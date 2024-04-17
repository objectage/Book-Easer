package com.Manager.Hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.source.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import com.Manager.Hotel.repository.BookingRepository;
import com.Manager.Hotel.service.RoomService;
import com.Manager.Hotel.service.CustomerService;
import com.Manager.Hotel.service.MainService;
import com.Manager.Hotel.entity.Booking;
import com.Manager.Hotel.entity.Customer;
import com.Manager.Hotel.entity.Hotel;
import com.Manager.Hotel.entity.Room;
// import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private BookingRepository bookingRepository;
   
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Main/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String login(HttpSession session, @ModelAttribute("customer") Customer customer) {
        Customer loggedInCustomer = mainService.login(customer);
        if (loggedInCustomer != null) {
            session.setAttribute("customer", loggedInCustomer); 
            return "redirect:/home";
        } else {
            session.setAttribute("error", "Invalid email or password"); 
            return "redirect:/login";
        }
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "Main/register";
    }
    

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute("customer") Customer customer) {
        if (customer != null && customerService.getCustomerByEmail(customer.getEmail()) == null){
            customer.setSub(false);
            customer = mainService.createAccount(customer);
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Invalid detials");
            return "redirect:/register";
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Hotel> hotels = mainService.getHotels();
        model.addAttribute("hotel", hotels);
        return "Main/home";
    }

    @PostMapping("/home/room")
    public String showRoomsForHotel(@RequestParam("hotelId") Long hotelId, Model model) {
        model.addAttribute("hotelId", hotelId);
        List<Room> rooms = mainService.getRoomsByHotelID(hotelId);
        model.addAttribute("rooms", rooms);
        return "Main/room"; 
    }

    @GetMapping("/home/room/available")
    public String showAvailableRoomsForHotel(@RequestParam("hotelId") Long hotelId, Model model) {
        model.addAttribute("hotelId", hotelId);
        List<Room> rooms = mainService.getRoomsByAvailability(true, hotelId);
        model.addAttribute("rooms", rooms);
        return "Main/room_available"; 
    }

    
    
    @PostMapping("/home/bookRoom")
    public String bookRoom(@RequestParam("roomId") Long roomId, HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer"); // Get from session
        if (customer == null) {
            return "redirect:/login";
        }
        Booking booking = new Booking();
        booking.setRoom(roomService.getRoom(roomId));
        booking.setCustomer(customer);
        // System.out.println(booking.getRoom().getId());
        // System.out.println(booking.getCustomer().getId());
        model.addAttribute("booking", booking);
        return "Main/book_room";
}

    @PostMapping("/datecheck")
    @ResponseBody
    public boolean checkRoomAvailability(
        @RequestParam("roomId") Long roomId,
        @RequestParam("startDate") String startDate,
        @RequestParam("noOfDays") int noOfDays) {
        LocalDate start = LocalDate.parse(startDate);
        
        return mainService.checkAvailability(roomId, start, noOfDays);
    }



    @PostMapping("/home/confirmBooking")
    public String bookingSuccess(Model model, @ModelAttribute Booking booking,
                                @RequestParam("startDate") String startDate,
                                @RequestParam("noOfDays") int noOfDays,
                                @RequestParam("roomid") Long roomId, 
                                @RequestParam("customerid") Long customerId) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = formatter.parse(startDate);
            booking.setStartDate(date);
            System.out.println(booking.getStartDate());

            Room room = roomService.getRoom(roomId); 
            booking.setRoom(room);
            System.out.println(booking.getRoom().getId());

            Customer customer = customerService.getCustomer(customerId);
            booking.setCustomer(customer);
            System.out.println(booking.getCustomer().getId());
            
            if(room == null || customer == null) {
                model.addAttribute("error", "Room or Customer not found");
                return "Main/book_room";
            }
            
            booking.setNoOfDays(noOfDays);
            booking.setTotalPrice(room.getPrice() * noOfDays);

            System.out.println(booking.getNoOfDays());
            System.out.println(booking.getTotalPrice());

            mainService.makeBooking(booking);
            model.addAttribute("booking", booking);
            return "redirect:/booking_success/" + booking.getId();
        } catch (Exception e) {
            model.addAttribute("error", "Failed to parse start date");
            return "Main/book_room";
        }
    }

    @GetMapping("/booking_success/{bookingId}")
    public String bookingSuccess(@PathVariable Long bookingId, Model model) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if (bookingOptional.isPresent()) {
            model.addAttribute("booking", bookingOptional.get());
        } else {
            model.addAttribute("error", "Booking not found.");
            return "error_page"; 
        }

        return "Main/booking_success";
    }

    @GetMapping("/download_receipt/{bookingId}")
    public void downloadReceipt(@PathVariable Long bookingId, HttpServletResponse response) {
        try {
            ByteArrayInputStream bis = generateReceipt(bookingId);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=booking_receipt_" + bookingId + ".pdf");

            OutputStream os = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public ByteArrayInputStream generateReceipt(Long bookingId) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        Booking booking = mainService.getBooking(bookingId);
    
        document.add(new Paragraph("Booking Receipt"));
        document.add(new AreaBreak());
        document.add(new Paragraph("Booking ID: " + booking.getId()));
        document.add(new Paragraph("Customer: " + booking.getCustomer().getName()));
        document.add(new Paragraph("Email: " + booking.getCustomer().getEmail()));
        document.add(new Paragraph("Hotel: " + booking.getRoom().getHotel().getName()));
        document.add(new Paragraph("Room Type: " + booking.getRoom().getType()));
        document.add(new Paragraph("Price per night: " + booking.getRoom().getPrice()));
        document.add(new Paragraph("Total Price: " + booking.getTotalPrice()));
        document.add(new Paragraph("Check In Date: " + booking.getStartDate().toString()));
        document.add(new Paragraph("Duration: " + booking.getNoOfDays() + " nights"));
    
        document.close();
    
        return new ByteArrayInputStream(out.toByteArray());
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        List<Booking> bookings = mainService.getBookingsByCustomerID(((Customer) session.getAttribute("customer")).getId());
        model.addAttribute("customer", session.getAttribute("customer"));
        model.addAttribute("bookings", bookings);
        return "Main/dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "Admin/adminhome";
    }

    @PostMapping("/subscribe")
    public String subcribe(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            mainService.setSubcriptionCustomer(customer.getId(), true);
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/unsubscribe")
    public String unsubcribe(Model model, HttpSession session){
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            mainService.setSubcriptionCustomer(customer.getId(), false);
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/hotelmanager/subscription")
    public String toggleSubscription(Model model, HttpSession session) {
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            System.out.println(customer.isSubscribed());
            session.setAttribute("customer",mainService.setSubcriptionCustomer(customer.getId(),!customer.isSubscribed())); 
        }
        return "redirect:/dashboard"; 
    }

}
