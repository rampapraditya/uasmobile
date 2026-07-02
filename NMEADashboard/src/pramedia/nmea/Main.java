package pramedia.nmea;

import pramedia.nmea.komponen.KomponenPeta;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Rampa Praditya
 */
public class Main extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Main.class.getName());
    
    private final KomponenPeta komponenPeta1;
    private SerialPort activePort = null;
    private final StringBuilder serialBuffer = new StringBuilder();

    public Main() {
        initComponents();
        LoadComPort();

        // 1. Inisialisasi komponen peta
        komponenPeta1 = new KomponenPeta();

        // 2. Atur layout panelTengah ke BorderLayout agar peta otomatis memenuhi layar
        panelTengah.setLayout(new java.awt.BorderLayout());
        
        // 3. Masukkan komponen peta ke dalam panelTengah penampung desainer NetBeans
        panelTengah.add(komponenPeta1, java.awt.BorderLayout.CENTER);
        
        // 4. Perbarui visual Swing agar peta langsung digambar seketika
        panelTengah.revalidate();
        panelTengah.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTengah = new javax.swing.JPanel();
        panelAtas = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnInfo = new javax.swing.JButton();
        panelBawah = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lbError = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        panelKanan = new javax.swing.JPanel();
        panelKiri = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lbCom = new javax.swing.JLabel();
        cbComPort = new javax.swing.JComboBox<>();
        lbBaudRate = new javax.swing.JLabel();
        cbBaudRate = new javax.swing.JComboBox<>();
        lbDataBits = new javax.swing.JLabel();
        cbDataBits = new javax.swing.JComboBox<>();
        lbStopBits = new javax.swing.JLabel();
        cbStopBits = new javax.swing.JComboBox<>();
        lbParityBits = new javax.swing.JLabel();
        cbParityBits = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnOpenClose = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtLog = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NMEA Dashboard");

        panelTengah.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelTengah, java.awt.BorderLayout.CENTER);

        panelAtas.setPreferredSize(new java.awt.Dimension(838, 35));
        panelAtas.setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pramedia/nmea/icon/globe.png"))); // NOI18N
        btnInfo.setFocusable(false);
        btnInfo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInfo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnInfo);

        panelAtas.add(jToolBar1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelAtas, java.awt.BorderLayout.PAGE_START);

        panelBawah.setPreferredSize(new java.awt.Dimension(657, 32));
        panelBawah.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(250, 100));
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lbError.setText(" -");
        jPanel1.add(lbError);

        panelBawah.add(jPanel1, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        panelBawah.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelBawah, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout panelKananLayout = new javax.swing.GroupLayout(panelKanan);
        panelKanan.setLayout(panelKananLayout);
        panelKananLayout.setHorizontalGroup(
            panelKananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelKananLayout.setVerticalGroup(
            panelKananLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 461, Short.MAX_VALUE)
        );

        getContentPane().add(panelKanan, java.awt.BorderLayout.LINE_END);

        panelKiri.setPreferredSize(new java.awt.Dimension(250, 273));
        panelKiri.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(170, 200));
        jPanel4.setLayout(new java.awt.GridLayout(7, 2, 5, 5));

        lbCom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbCom.setText("Com Port");
        jPanel4.add(lbCom);

        jPanel4.add(cbComPort);

        lbBaudRate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbBaudRate.setText("Baud Rate");
        jPanel4.add(lbBaudRate);

        cbBaudRate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4800", "9600", "38400", "57600", "115200" }));
        cbBaudRate.setSelectedIndex(1);
        jPanel4.add(cbBaudRate);

        lbDataBits.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbDataBits.setText(" Data Bits");
        jPanel4.add(lbDataBits);

        cbDataBits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "6", "7", "8" }));
        cbDataBits.setSelectedIndex(2);
        jPanel4.add(cbDataBits);

        lbStopBits.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbStopBits.setText(" Stop Bits");
        jPanel4.add(lbStopBits);

        cbStopBits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "1.5", "2" }));
        jPanel4.add(cbStopBits);

        lbParityBits.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbParityBits.setText(" Parity Bits");
        jPanel4.add(lbParityBits);

        cbParityBits.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO", "EVEN", "ODD", "MARK", "SPACE" }));
        jPanel4.add(cbParityBits);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel5);

        btnOpenClose.setText("Open");
        btnOpenClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenCloseActionPerformed(evt);
            }
        });
        jPanel4.add(btnOpenClose);

        panelKiri.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel6.setPreferredSize(new java.awt.Dimension(170, 250));
        jPanel6.setLayout(new java.awt.BorderLayout());

        txtLog.setColumns(20);
        txtLog.setRows(5);
        jScrollPane1.setViewportView(txtLog);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelKiri.add(jPanel6, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelKiri, java.awt.BorderLayout.LINE_START);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenCloseActionPerformed
        togglePortConnection();
    }//GEN-LAST:event_btnOpenCloseActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        Info dialogInfo = new Info(this, true);
        dialogInfo.setLocationRelativeTo(this);
        dialogInfo.setVisible(true);
    }//GEN-LAST:event_btnInfoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnOpenClose;
    private javax.swing.JComboBox<String> cbBaudRate;
    private javax.swing.JComboBox<String> cbComPort;
    private javax.swing.JComboBox<String> cbDataBits;
    private javax.swing.JComboBox<String> cbParityBits;
    private javax.swing.JComboBox<String> cbStopBits;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbBaudRate;
    private javax.swing.JLabel lbCom;
    private javax.swing.JLabel lbDataBits;
    private javax.swing.JLabel lbError;
    private javax.swing.JLabel lbParityBits;
    private javax.swing.JLabel lbStopBits;
    private javax.swing.JPanel panelAtas;
    private javax.swing.JPanel panelBawah;
    private javax.swing.JPanel panelKanan;
    private javax.swing.JPanel panelKiri;
    private javax.swing.JPanel panelTengah;
    private javax.swing.JTextArea txtLog;
    // End of variables declaration//GEN-END:variables

    private void LoadComPort() {
        cbComPort.removeAllItems();
        cbComPort.addItem("Memindai port...");
        cbComPort.setEnabled(false);

        // Buat SwingWorker: <Hasil Akhir, Data Sementara>
        SwingWorker<String[], Void> worker = new SwingWorker<>() {
            @Override
            protected String[] doInBackground() throws Exception {
                // Proses berat/pemindaian dilakukan di background thread
                SerialPort[] portList = SerialPort.getCommPorts();
                String[] portNames = new String[portList.length];
                for (int i = 0; i < portList.length; i++) {
                    portNames[i] = portList[i].getSystemPortName();
                }
                return portNames;
            }

            @Override
            protected void done() {
                // Kembali ke EDT, aman untuk memperbarui UI
                try {
                    String[] ports = get(); // Mengambil hasil dari doInBackground()
                    cbComPort.removeAllItems(); // Hapus teks "Memindai..."

                    if (ports.length == 0) {
                        cbComPort.addItem("Tidak ada port");
                    } else {
                        for (String port : ports) {
                            cbComPort.addItem(port);
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    cbComPort.removeAllItems();
                    cbComPort.addItem("Gagal memuat port");
                } finally {
                    cbComPort.setEnabled(true);
                }
            }
        };

        // Jalankan worker
        worker.execute();
    }

    private int getSelectedDataBits() {
        return Integer.parseInt(cbDataBits.getSelectedItem().toString());
    }

    private int getSelectedStopBits() {
        String selected = cbStopBits.getSelectedItem().toString();
        if (selected.contains("1.5")) {
            return SerialPort.ONE_POINT_FIVE_STOP_BITS;
        }
        if (selected.contains("2")) {
            return SerialPort.TWO_STOP_BITS;
        }
        return SerialPort.ONE_STOP_BIT;
    }

    private int getSelectedParity() {
        String selected = cbParityBits.getSelectedItem().toString().toUpperCase();
        if (selected.equals("ODD")) {
            return SerialPort.ODD_PARITY;
        }
        if (selected.equals("EVEN")) {
            return SerialPort.EVEN_PARITY;
        }
        if (selected.equals("MARK")) {
            return SerialPort.MARK_PARITY;
        }
        if (selected.equals("SPACE")) {
            return SerialPort.SPACE_PARITY;
        }
        return SerialPort.NO_PARITY;
    }

    private void writeLog(String message) {
        // Jalankan di EDT agar aman untuk UI Swing
        SwingUtilities.invokeLater(() -> {
            String time = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
            // Format log: [14:30:22.123] -> Hello World
            txtLog.append(String.format("[%s]\n", time));
            txtLog.append(String.format("%s\n", message));

            // Otomatis scroll ke baris paling bawah
            txtLog.setCaretPosition(txtLog.getDocument().getLength());
        });
    }

    private void togglePortConnection() {
        if (activePort != null && activePort.isOpen()) {
            // --- PROSES TOMBOL CLOSE ---
            btnOpenClose.setEnabled(false);
            btnOpenClose.setText("Closing...");

            SwingWorker<Boolean, Void> closeWorker = new SwingWorker<>() {
                @Override
                protected Boolean doInBackground() {
                    if (activePort != null) {
                        activePort.removeDataListener(); // Hapus listener sebelum tutup
                        return activePort.closePort();
                    }
                    return true;
                }

                @Override
                protected void done() {
                    try {
                        if (get()) {
                            activePort = null;
                            setComponentsEnabled(true);
                            btnOpenClose.setText("Open");
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal menutup port!");
                            btnOpenClose.setText("Close");
                        }
                    } catch (HeadlessException | InterruptedException | ExecutionException e) {
                        lbError.setText(e.getMessage());
                    } finally {
                        btnOpenClose.setEnabled(true);
                    }
                }
            };
            closeWorker.execute();

        } else {
            // --- PROSES TOMBOL OPEN ---
            if (cbComPort.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Pilih COM Port terlebih dahulu!");
                return;
            }

            String portName = cbComPort.getSelectedItem().toString();
            int baudRate = Integer.parseInt(cbBaudRate.getSelectedItem().toString());

            btnOpenClose.setEnabled(false);
            btnOpenClose.setText("Opening...");

            // DI SINI TEMPAT SWINGWORKER UNTUK OPEN PORT
            SwingWorker<Boolean, Void> openWorker = new SwingWorker<>() {

                // 1. DI SINI ADALAH TEMPAT doInBackground() UNTUK OPEN PORT
                @Override
                protected Boolean doInBackground() {
                    activePort = SerialPort.getCommPort(portName);

                    // Set parameter port sesuai pilihan di UI
                    activePort.setBaudRate(baudRate);
                    activePort.setNumDataBits(getSelectedDataBits());
                    activePort.setNumStopBits(getSelectedStopBits());
                    activePort.setParity(getSelectedParity());

                    // ISI LISTENER UNTUK MENERIMA DATA MASUK (RECEIVE)
                    activePort.addDataListener(new com.fazecast.jSerialComm.SerialPortDataListener() {
                        @Override
                        public int getListeningEvents() {
                            return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                        }

                        @Override
                        public void serialEvent(com.fazecast.jSerialComm.SerialPortEvent event) {
                            if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                                return;
                            }

                            int bytesAvailable = activePort.bytesAvailable();
                            if (bytesAvailable <= 0) {
                                return;
                            }

                            byte[] readBuffer = new byte[bytesAvailable];
                            int numRead = activePort.readBytes(readBuffer, readBuffer.length);

                            if (numRead > 0) {
                                String receivedData = bytesToString(readBuffer);
                                writeLog(receivedData);

                                // Langsung lempar teks data mentah ke fungsi parse baru kita
                                parseNMEA(receivedData);
                            }
                        }
                    });

                    // Buka port hardware di background thread
                    return activePort.openPort();
                }

                // 2. DI SINI PASANGANNYA, YAITU method done() UNTUK UPDATE UI SUKSES/GAGAL
                @Override
                protected void done() {
                    try {
                        if (get()) { // get() mengambil return true/false dari doInBackground
                            setComponentsEnabled(false); // Kunci semua combobox
                            btnOpenClose.setText("Close");
                            writeLog("Port berhasil dibuka. Menunggu data...");
                        } else {
                            JOptionPane.showMessageDialog(null, "Gagal membuka port! Terpakai aplikasi lain?");
                            activePort = null;
                            btnOpenClose.setText("Open");
                        }
                    } catch (HeadlessException | InterruptedException | ExecutionException e) {
                        lbError.setText(e.getMessage());
                        btnOpenClose.setText("Open");
                    } finally {
                        btnOpenClose.setEnabled(true);
                    }
                }
            };

            // Jalankan SwingWorker-nya
            openWorker.execute();
        }
    }

    private void setComponentsEnabled(boolean enabled) {
        cbComPort.setEnabled(enabled);
        cbBaudRate.setEnabled(enabled);
        cbDataBits.setEnabled(enabled);
        cbStopBits.setEnabled(enabled);
        cbParityBits.setEnabled(enabled);
    }

    private String bytesToString(byte[] buffer) {
        // Menggunakan UTF-8 atau StandardCharsets.US_ASCII tergantung jenis device Anda
        return new String(buffer, java.nio.charset.StandardCharsets.UTF_8).trim();
    }

    
    // 2. PERBAIKI FUNGSI PARSE NMEA ANDA
    private void parseNMEA(String receivedChunk) {
        // Masukkan potongan data serial yang baru masuk ke dalam buffer utama
        serialBuffer.append(receivedChunk);
        String dataInput = serialBuffer.toString();

        // Pecah berdasarkan baris baru (\n atau \r\n)
        String[] lines = dataInput.split("\\r?\\n");

        // Jika baris terakhir tidak diakhiri dengan baris baru, berarti datanya terpotong
        if (!dataInput.endsWith("\n") && !dataInput.endsWith("\r")) {
            // Simpan sisa potongan baris terakhir ke buffer untuk penyambungan berikutnya
            serialBuffer.setLength(0);
            serialBuffer.append(lines[lines.length - 1]);
            // Buang baris terpotong dari antrean proses saat ini
            lines = java.util.Arrays.copyOf(lines, lines.length - 1);
        } else {
            // Jika bersih diakhiri enter, kosongkan buffer untuk data baru nanti
            serialBuffer.setLength(0);
        }

        // Proses setiap baris data NMEA yang utuh
        for (String sentence : lines) {
            sentence = sentence.trim();
            if (!sentence.startsWith("$")) {
                continue;
            }

            String[] tokens = sentence.split(",");
            String header = tokens[0];

            try {
                // --- PARSING DATA GGA ---
                if (header.endsWith("GGA")) {
                    if (tokens.length > 9 && !tokens[2].isEmpty() && !tokens[4].isEmpty()) {
                        String utcTime = tokens[1];
                        String fix = tokens[6]; // 1 = Fix, 0 = No Fix
                        String sat = tokens[7]; // Jumlah Satelit
                        String alt = tokens[9] + " Meter";

                        // Konversi string NMEA ke angka double desimal
                        double latitude = convertToDecimal(tokens[2], tokens[3]); // Kolom 2 & 3
                        double longitude = convertToDecimal(tokens[4], tokens[5]); // Kolom 4 & 5

                        System.out.println("Satelit : " + sat + " | Lat: " + latitude + " Lon: " + longitude);

                        // Kirim koordinat ke JXMapViewer di Thread UI
                        if (!fix.equals("0")) { // Pastikan GPS mendapat sinyal valid
                            SwingUtilities.invokeLater(() -> {
                                komponenPeta1.perbaruiLokasiGps(latitude, longitude);
                            });
                        }
                    }
                } // --- PARSING DATA RMC ---
                else if (header.endsWith("RMC")) {
                    if (tokens.length > 9) {
                        String status = tokens[2]; // A = Valid, V = Invalid

                        if (status.equals("A") && !tokens[3].isEmpty() && !tokens[5].isEmpty()) {
                            String speed = tokens[7] + " Knots";
                            String date = tokens[9];

                            double latitude = convertToDecimal(tokens[3], tokens[4]); // Kolom 3 & 4
                            double longitude = convertToDecimal(tokens[5], tokens[6]); // Kolom 5 & 6

                            System.out.println("Speed : " + speed + " | Lat: " + latitude + " Lon: " + longitude);

                            // Kirim koordinat ke JXMapViewer di Thread UI
                            SwingUtilities.invokeLater(() -> {
                                komponenPeta1.perbaruiLokasiGps(latitude, longitude);
                                lbError.setText("Sinyal Terhubung - Kecepatan: " + speed);
                            });
                        } else {
                            SwingUtilities.invokeLater(() -> {
                                lbError.setText("[RMC] Sinyal GPS Belum Valid (Mencari Satelit...)");
                            });
                        }
                    }
                }
            } catch (Exception e) {
                System.err.println("Gagal parsing baris: " + sentence + " | Error: " + e.getMessage());
            }
        }
    }

    // 3. TAMBAHKAN METHOD HELPER UNTUK MENGUBAH FORMAT KOORDINAT
    private double convertToDecimal(String rawValue, String direction) {
        if (rawValue == null || rawValue.isEmpty()) {
            return 0.0;
        }

        double raw = Double.parseDouble(rawValue);
        // Format NMEA Lintang: DDMM.MMMM, Bujur: DDDMM.MMMM
        // Kita potong 2 angka di depan koma sebagai Menit, sisanya Derajat
        int degrees = (int) (raw / 100);
        double minutes = raw - (degrees * 100);

        double decimal = degrees + (minutes / 60.0);

        // Jika berada di belahan bumi Selatan (S) atau Barat (W), nilainya negatif
        if (direction.equalsIgnoreCase("S") || direction.equalsIgnoreCase("W")) {
            decimal = -decimal;
        }

        return decimal;
    }

}
