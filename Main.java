import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    private String id;
    private String name;
    private int age;
    private String ailment;

    public Patient(String id, String name, int age, String ailment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.ailment = ailment;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getAilment() { return ailment; }

    @Override
    public String toString() {
        return "Hasta ID: " + id + " | İsim: " + name + " | Yaş: " + age + " | Şikayet: " + ailment;
    }
}

class Doctor {
    private String id;
    private String name;
    private String specialty;

    public Doctor(String id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }

    @Override
    public String toString() {
        return "Doktor ID: " + id + " | İsim: " + name + " | Uzmanlık: " + specialty;
    }
}

class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String date;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Randevu ID: " + appointmentId + " | Tarih: " + date + 
               "\n   -> Hasta: " + patient.getName() + 
               "\n   -> Doktor: " + doctor.getName() + " (" + doctor.getSpecialty() + ")";
    }
}

public class Main {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        doctors.add(new Doctor("D1", "Dr. Ali Yilmaz", "Kardiyoloji"));
        doctors.add(new Doctor("D2", "Dr. Ayse Kaya", "Noroloji"));

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== HASTANE YONETIM SISTEMI ===");
            System.out.println("1. Yeni Hasta Ekle");
            System.out.println("2. Doktorlari Listele");
            System.out.println("3. Hastalari Listele");
            System.out.println("4. Randevu Olustur");
            System.out.println("5. Randevulari Listele");
            System.out.println("6. Cikis");
            System.out.print("Seciminiz: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewDoctors();
                    break;
                case 3:
                    viewPatients();
                    break;
                case 4:
                    scheduleAppointment();
                    break;
                case 5:
                    viewAppointments();
                    break;
                case 6:
                    isRunning = false;
                    System.out.println("Sistemden cikiliyor. Iyi gunler!");
                    break;
                default:
                    System.out.println("Gecersiz secim. Lutfen tekrar deneyin.");
            }
        }
    }

    private static void addPatient() {
        System.out.print("Hasta ID: ");
        String id = scanner.nextLine();
        System.out.print("Hasta Ismi: ");
        String name = scanner.nextLine();
        System.out.print("Hasta Yasi: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Sikayet: ");
        String ailment = scanner.nextLine();

        patients.add(new Patient(id, name, age, ailment));
        System.out.println("Hasta basariyla eklendi!");
    }

    private static void viewDoctors() {
        System.out.println("\n--- Kayitli Doktorlar ---");
        for (Doctor d : doctors) {
            System.out.println(d.toString());
        }
    }

    private static void viewPatients() {
        System.out.println("\n--- Kayitli Hastalar ---");
        if (patients.isEmpty()) {
            System.out.println("Sistemde kayitli hasta bulunmuyor.");
            return;
        }
        for (Patient p : patients) {
            System.out.println(p.toString());
        }
    }

    private static void scheduleAppointment() {
        System.out.print("Randevu ID: ");
        String appointmentId = scanner.nextLine();
        
        System.out.print("Hasta ID girin: ");
        String patientId = scanner.nextLine();
        Patient selectedPatient = null;
        for (Patient p : patients) {
            if (p.getId().equals(patientId)) {
                selectedPatient = p;
                break;
            }
        }

        System.out.print("Doktor ID girin: ");
        String doctorId = scanner.nextLine();
        Doctor selectedDoctor = null;
        for (Doctor d : doctors) {
            if (d.getId().equals(doctorId)) {
                selectedDoctor = d;
                break;
            }
        }

        if (selectedPatient != null && selectedDoctor != null) {
            System.out.print("Randevu Tarihi (Orn: 10-05-2026): ");
            String date = scanner.nextLine();
            
            appointments.add(new Appointment(appointmentId, selectedPatient, selectedDoctor, date));
            System.out.println("Randevu basariyla olusturuldu!");
        } else {
            System.out.println("Hata: Gecersiz Hasta veya Doktor ID'si girdiniz.");
        }
    }

    private static void viewAppointments() {
        System.out.println("\n--- Randevu ve Raporlar ---");
        if (appointments.isEmpty()) {
            System.out.println("Sistemde kayitli randevu bulunmuyor.");
            return;
        }
        for (Appointment a : appointments) {
            System.out.println(a.toString());
            System.out.println("-------------------------");
        }
    }
}