public class Appointment {
    String time;
    String newPatient;
    String sick;
    String clinic;
    String doctor;
    String fullName;
    String phoneNumber;
    String reason;
    String insurancePlan;
    String medicalRecords;
    String medication;

    public Appointment(String time, String newPatient, String sick, String clinic, String doctor, String fullName, String phoneNumber, String reason, String insurancePlan, String medicalRecords, String medication) {
        this.time = time;
        this.newPatient = newPatient;
        this.sick = sick;
        this.clinic = clinic;
        this.doctor = doctor;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.reason = reason;
        this.insurancePlan = insurancePlan;
        this.medicalRecords = medicalRecords;
        this.medication = medication;
    }
}
