public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        Switchable pj = (Switchable) reg.getFirstOfType("Projector");
        pj.powerOn();
        ((InputConnectable) pj).connectInput("HDMI-1");

        BrightnessControl lights = (BrightnessControl) reg.getFirstOfType("LightsPanel");
        lights.setBrightness(60);

        TemperatureControl ac = (TemperatureControl) reg.getFirstOfType("AirConditioner");
        ac.setTemperatureC(24);

        Scanner scan = (Scanner) reg.getFirstOfType("AttendanceScanner");
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        ((Switchable) reg.getFirstOfType("Projector")).powerOff();
        ((Switchable) reg.getFirstOfType("LightsPanel")).powerOff();
        ((Switchable) reg.getFirstOfType("AirConditioner")).powerOff();
    }
}
