package servlet;

import entity.Car;
import entity.CarService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CarServlet extends HttpServlet {

    public CarService carService = new CarService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeCook(resp);
        List<Car> cars = carService.getAll();
        PrintWriter writer = resp.getWriter();
        for (Car car : cars) {
            writer.println("Car: id = " + car.getId() + " brand = " + car.getBrand());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeCook(resp);
        PrintWriter writer = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        Car car = carService.getCarById(id);
        writer.println("Car: id = " + car.getId() + " brand = " + car.getBrand());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeCook(resp);
        List<Car> cars = carService.getAll();
        PrintWriter writer = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        String brand = req.getParameter("brand");
        if (cars.stream().anyMatch(car -> car.getId() == Integer.parseInt(req.getParameter("id")))){
            Car carForUpdate = carService.getCarById(id);
            carForUpdate.setBrand(brand);
            writer.println("Car update : id = " + id + " brand = " + brand);
        } else {
            carService.createCar(id, brand);
            writer.println("Car create : id = " + id + " brand = " + brand);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        writeCook(resp);
        PrintWriter writer = resp.getWriter();
        carService.deleteCar(Integer.parseInt(req.getParameter("id")));
        writer.println("car remove");
    }

    private HttpServletResponse writeCook(HttpServletResponse resp) {
        String patternDate = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormatDate = new SimpleDateFormat(patternDate);
        String date = simpleDateFormatDate.format(new Date());

        String patternTime = "HH:mm:ss";
        SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat(patternTime);
        String time = simpleDateFormatTime.format(new Date());

        resp.addCookie(new Cookie("Date", date));
        resp.addCookie(new Cookie("Time", time));

        return resp;
    }
}
