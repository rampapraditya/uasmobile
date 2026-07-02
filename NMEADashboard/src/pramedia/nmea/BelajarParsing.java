package pramedia.nmea;

/**
 *
 * @author FTI05
 */
public class BelajarParsing {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String kalimat = "Pada hari minggu ku turut ayah ke kantor. Naik supra bapak istimewa.";
        
        // hitung jumlah karakter
        int jml_karakter = kalimat.length();
        System.out.println("JML KARAKTER " + jml_karakter);
        
        // deteksi jumlah kata
        String[] potongKata = kalimat.split(" ");
        System.out.println("JML KATA " + potongKata.length);
        
        // deteksi kata pertama
        String kataPertama = potongKata[0];
        System.out.println("KATA PERTAMA " + kataPertama);
        
        String kataTerakhir = potongKata[potongKata.length - 1];
        System.out.println("KATA TERAKHIR " + kataTerakhir);
        
        // deteksi jumlah kalimat
        String[] potongKalimat = kalimat.split("\\.");
        System.out.println("JML KALIMAT " + potongKalimat.length);
        
        String nmea = "$GPRMC,074625.386,A,0715.024,S,11246.128,E,8.1,90.0,230626,,,A*41";
    }
    
}
