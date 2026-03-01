public class TransportBookingService {
    private final DistanceService distSvc;
    private final DriverService driverSvc;
    private final PaymentService paySvc;

    public TransportBookingService(DistanceService distSvc, DriverService driverSvc, PaymentService paySvc) {
        this.distSvc = distSvc;
        this.driverSvc = driverSvc;
        this.paySvc = paySvc;
    }

    public void book(TripRequest req) {
        double km = distSvc.km(req.from, req.to);
        System.out.println("DistanceKm=" + km);

        String driver = driverSvc.allocate(req.studentId);
        System.out.println("Driver=" + driver);

        double fare = 50.0 + km * 6.6666666667;
        fare = Math.round(fare * 100.0) / 100.0;

        String txn = paySvc.charge(req.studentId, fare);
        System.out.println("Payment=PAID txn=" + txn);

        BookingReceipt r = new BookingReceipt("R-501", fare);
        System.out.println("RECEIPT: " + r.id + " | fare=" + String.format("%.2f", r.fare));
    }
}
