public class Demo07 {
    public static void main(String[] args) {
        System.out.println("=== Smart Classroom ===");

        DeviceRegistry reg = new DeviceRegistry();
        reg.add(new Projector());
        reg.add(new LightsPanel());
        reg.add(new AirConditioner());
        reg.add(new AttendanceScanner());

        ClassroomController ctrl = new ClassroomController(reg);
        ctrl.startClass();
        ctrl.endClass();
    }
}
