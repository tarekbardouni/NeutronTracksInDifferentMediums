package MCTrackSimulation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Tarek El Bardouni, Email: tarekbardouni@uae.ma ERSN, University
 * Abdelmalek Essaadi, Tetouan, Morocco
 *
 *
 */
public class MonteCarloScatterPlot extends javax.swing.JFrame implements ActionListener {

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/YY");
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    static Boolean CancelFlag = false;
    static Boolean ProcessFlag = false;
    static Boolean ClearFlag = false;
    static Boolean SaveFlag = false;
    static Boolean Material_Selected = false;
    static Boolean ContinueFlag = false;
    static Boolean FirstNeutronFlag;
    static Boolean LeakageFlag = false;
    public static long Batch_Size_total, Batch_Size_init, Batch_Size;
    public static long FirstCollisionNumber, CollisionNumber, CaptureNumber, TotalCollisionNumber, TotalCaptureNumber, FirstNeutronLeakageNumber, LeakageNumber, NeutronKilledEnergyCutoff;
    public static long Start_I, continue_counter;
    public static double AverageCollisionNumber;
    public static double Slab_Thickness, Slab_Length, HalfSlabLength;
    public static double Track_length, x, y, z, R;
    public static double NeutronEnergyIn, IncidentNeutronEnergy, NeutronEnergyOut, NeutronEnergyTot, NeutronSpeed, NeutronSpeedTotal, NeutronLifeTimeTotal, NeutronTrackLengthTotal, ECutOff = 1.;
    public static double theta_CM, theta_LAB, phi, sin_theta_CM, muCM, sin_theta_LAB, muLAB, sin_phi, cos_phi, u, v, w, up, vp, wp;
    public static double Isotope_coef[];
    public static Integer Isotope_index, ZAID[];
    public static int myEnergyList_Size[];
    public static Integer nE[] = {0, 0};
    public static long Energy_in_size;
    public static Double[][] Energy_in, Sigma_Tot, Sigma_Scat, Sigma_Abs;
    public static Double[][] xEnergy, ySigmaTot, ySigmaScat, ySigmaAbs;
    public static double d[];
    public static List<Integer> myIsotopesList = new ArrayList();
    public static List<Double> myEnergyList = new ArrayList();
    public static List<Double> myTotalList = new ArrayList();
    public static List<Double> myElasticList = new ArrayList();
    public static List<Double> myAbsorptionList = new ArrayList();
    public XYSeries series1, series2;
    public XYSeries xyCollisionSerie = new XYSeries("  Collision   ");
    public XYSeries xyFirstCollisionSerie = new XYSeries("  First Collision   ");
    public XYSeries xyLeakageSerie = new XYSeries("  Leakage   ");
    public XYSeries xyCapturedSerie = new XYSeries("  Capture   ");
    public static Integer Material_ID, CollidedNucleiID;
    public static Double Nuclei_Mass, Neutron_mass = 1.675E-27;
    public static Double Avogadro = 6.02E+23, MaterialAtomDensity = 0.0;
    public String material_name[] = {"Air", "Aluminium", "Cadmium", "Graphite", "Iron", "Lead", "Water"};
    public int ID[] = {15, 13027, 48000, 6000, 26056, 82000, 18};
    public Double Molar_mass[] = {28.98, 26.981538, 112.4115549, 12.000000, 55.934942, 207.2168966, 18.010565};
    public Double Density[] = {0.001225, 2.7, 8.64, 1.6, 7.87, 11.35, 0.997};
    public String Isotope_name[] = {"H-1", "C-Nat", "N-14", "O-16", "Al-27", "Fe-56", "Cd-Nat", "Pb-Nat"};
    public Double Isotope_mass[] = {1.007825, 12.000000, 14.003074, 15.994915, 26.981538, 55.934942, 112.4115549, 207.2168966};
    public static List<Integer> Isotope_ID = Arrays.asList(new Integer[]{1001, 6000, 7014, 8016, 13027, 26056, 48000, 82000});
    public Double ComponentAtomDensity[] = {0., 0.}, SigmaMicroIsotopeTotal[] = {0., 0.}, SigmaMicroIsotopeScattering[] = {0., 0.}, SigmaMacroIsotopeTotal[] = {0., 0.}, SigmaMicroMaterialTotal = 0., SigmaMacroMaterialTotal = 0.;
    long elapsedTime, startTime, endTime;
    public int nPoints;
    public double[] xE = null;
    public double[] yS = null;
    public double[] d2ydx2 = null;

    public MonteCarloScatterPlot() {
        initComponents();
        dateTF.setText(" " + dateFormat.format(date));
        setTitle("Monte Carlo simulation of neutron tracks");
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.validate();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        SlabThickness = new javax.swing.JTextField();
        batch_size = new javax.swing.JTextField();
        Incident_Neutron_Energy = new javax.swing.JTextField();
        FirstCollisionsLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        CutoffLabel = new javax.swing.JLabel();
        Simulation_Time = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        ProcessBtn = new javax.swing.JButton();
        BreakBtn = new javax.swing.JButton();
        ContinueBtn = new javax.swing.JButton();
        dateTF = new javax.swing.JTextField();
        ExitBtn = new javax.swing.JButton();
        clearDataBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Material_Matrix = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        SlabLength = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        LeakageLabel = new javax.swing.JLabel();
        FirstLeakageLabel = new javax.swing.JLabel();
        TotalLeakageLabel = new javax.swing.JLabel();
        AverageCollisionLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.lightGray);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 2, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("Monte Carlo Simulation of neutron tracks");

        jLabel2.setText("Slab thickness/cm");

        jLabel3.setText("Sample size");

        jLabel5.setText("Neutron energy/MeV");

        SlabThickness.setText("120");
        SlabThickness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SlabThicknessActionPerformed(evt);
            }
        });

        batch_size.setText("1000");

        Incident_Neutron_Energy.setText("2");

        CutoffLabel.setToolTipText("");

        jProgressBar1.setMaximumSize(new java.awt.Dimension(32767, 12));
        jProgressBar1.setPreferredSize(new java.awt.Dimension(146, 12));

        ProcessBtn.setText("process");
        ProcessBtn.setMaximumSize(new java.awt.Dimension(105, 29));
        ProcessBtn.setMinimumSize(new java.awt.Dimension(105, 29));
        ProcessBtn.setPreferredSize(new java.awt.Dimension(105, 29));
        ProcessBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProcessBtnActionPerformed(evt);
            }
        });

        BreakBtn.setText("cancel");
        BreakBtn.setMaximumSize(new java.awt.Dimension(105, 29));
        BreakBtn.setMinimumSize(new java.awt.Dimension(105, 29));
        BreakBtn.setPreferredSize(new java.awt.Dimension(105, 29));
        BreakBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BreakBtnActionPerformed(evt);
            }
        });

        ContinueBtn.setText("continue");
        ContinueBtn.setEnabled(false);
        ContinueBtn.setMaximumSize(new java.awt.Dimension(105, 29));
        ContinueBtn.setMinimumSize(new java.awt.Dimension(105, 29));
        ContinueBtn.setPreferredSize(new java.awt.Dimension(105, 29));
        ContinueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContinueBtnActionPerformed(evt);
            }
        });
        ContinueBtn.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ContinueBtnPropertyChange(evt);
            }
        });

        dateTF.setEditable(false);

        ExitBtn.setText("Exit");
        ExitBtn.setMaximumSize(new java.awt.Dimension(105, 29));
        ExitBtn.setMinimumSize(new java.awt.Dimension(105, 29));
        ExitBtn.setPreferredSize(new java.awt.Dimension(105, 29));
        ExitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitBtnActionPerformed(evt);
            }
        });

        clearDataBtn.setText("clear data");
        clearDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearDataBtnActionPerformed(evt);
            }
        });

        jLabel6.setText("Material ");

        Material_Matrix.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select material", "Air", "Aluminium", "Cadmium", "Graphite", "Iron", "Lead", "Water" }));
        Material_Matrix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Material_MatrixActionPerformed(evt);
            }
        });

        jLabel4.setText("Slab length/cm");

        SlabLength.setText("200");

        jSeparator3.setPreferredSize(new java.awt.Dimension(0, 6));

        LeakageLabel.setToolTipText("");

        FirstLeakageLabel.setSize(new java.awt.Dimension(45, 20));

        TotalLeakageLabel.setSize(new java.awt.Dimension(45, 20));

        AverageCollisionLabel.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LeakageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TotalLeakageLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addGap(33, 33, 33)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(SlabThickness)
                                            .addComponent(batch_size, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                            .addComponent(Incident_Neutron_Energy)
                                            .addComponent(SlabLength, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(35, 35, 35))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Material_Matrix, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addGap(100, 100, 100))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(FirstCollisionsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                    .addComponent(Simulation_Time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FirstLeakageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(clearDataBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(ExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(ProcessBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(BreakBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(ContinueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(CutoffLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AverageCollisionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(SlabThickness, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(SlabLength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(batch_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Incident_Neutron_Energy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(Material_Matrix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(FirstCollisionsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FirstLeakageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CutoffLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TotalLeakageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LeakageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AverageCollisionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Simulation_Time, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProcessBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BreakBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ContinueBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearDataBtn)
                    .addComponent(ExitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2))
        );

        TotalLeakageLabel.getAccessibleContext().setAccessibleDescription("");

        jPanel2.setBackground(new java.awt.Color(213, 216, 214));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(460, 460));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Material_MatrixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Material_MatrixActionPerformed
        CreateMaterialMatrix();
    }

    public void CreateMaterialMatrix() {
        Material_ID = Material_Matrix.getSelectedIndex();
        System.out.println("Material_ID: " + Material_ID);
        String Material = Material_Matrix.getItemAt(Material_Matrix.getSelectedIndex());
        switch (Material_ID) {
            case 1:    // Air
                myIsotopesList.removeAll(myIsotopesList);
                System.out.println(Material);
                Isotope_coef = new double[]{0.78, 0.22};
                myIsotopesList.add(0, 7014);
                myIsotopesList.add(1, 8016);
                Material_Selected = true;
                break;
            case 2:    // Aluminium
                myIsotopesList.removeAll(myIsotopesList);
                System.out.println(Material);
                Isotope_coef = new double[]{1};
                myIsotopesList.add(0, 13027);
                Material_Selected = true;
                break;
            case 3:    // Cadmium
                myIsotopesList.removeAll(myIsotopesList);
                System.out.println(Material);
                Isotope_coef = new double[]{1};
                myIsotopesList.add(0, 48000);
                Material_Selected = true;
                break;
            case 4:    // Graphite
                myIsotopesList.removeAll(myIsotopesList);
                System.out.println(Material);
                Isotope_coef = new double[]{1};
                myIsotopesList.add(0, 6000);
                Material_Selected = true;
                break;
            case 5:   // Iron
                myIsotopesList.removeAll(myIsotopesList);
                System.out.println(Material);
                Isotope_coef = new double[]{1};
                myIsotopesList.add(0, 26056);
                Material_Selected = true;
                break;
            case 6:   // Lead
                myIsotopesList.removeAll(myIsotopesList);
                System.out.println(Material);
                Isotope_coef = new double[]{1};
                myIsotopesList.add(0, 82000);
                Material_Selected = true;
                break;
            case 7:   // Water
                myIsotopesList.removeAll(myIsotopesList);
                System.out.println(Material);
                Isotope_coef = new double[]{2.0 / 3.0, 1.0 / 3.0};
                myIsotopesList.add(0, 1001);
                myIsotopesList.add(1, 8016);
                Material_Selected = true;
                break;
            default:
                Material_Selected = false;
                System.out.println("You need to choose one material");
        }

        //   int[] nE =  int[myIsotopesList.size()];
        if (Material_Selected) {
            Isotope_index = 0;
            ZAID = myIsotopesList.toArray(new Integer[myIsotopesList.size()]);
            xEnergy = new Double[2][1000000];
            Energy_in = new Double[2][1000000];
            Sigma_Tot = new Double[2][1000000];
            Sigma_Scat = new Double[2][1000000];
            Sigma_Abs = new Double[2][1000000];
            for (int k = 0; k < myIsotopesList.size(); k++) {
                try {
                    ReadData.ReadData();
                } catch (IOException ex) {
                    Logger.getLogger(MonteCarloScatterPlot.class.getName()).log(Level.SEVERE, null, ex);
                }
                nE[k] = ReadData.myEnergyList.size();

                System.out.println("Reading data for  :  " + myIsotopesList.get(k) + "  Number of raws :  " + nE[k]);
                for (int j = 0; j < (int) nE[k]; j++) {
                    Energy_in[k][j] = ReadData.myEnergyList.get(j);   //d[j];                  
                    xEnergy[k][j] = ReadData.myEnergyList.get(j);
                    Sigma_Tot[k][j] = ReadData.myTotalList.get(j);
                    Sigma_Scat[k][j] = ReadData.myElasticList.get(j);
                    Sigma_Abs[k][j] = ReadData.myAbsorptionList.get(j);
                }
                Isotope_index++;
                System.out.println("Reading terminated for  :  " + myIsotopesList.get(k));
            }
            for (int k = 0; k < myIsotopesList.size(); k++) {
                xEnergy[k] = Arrays.copyOf(xEnergy[k], nE[k]);
                Energy_in[k] = Arrays.copyOf(Energy_in[k], nE[k]);
                Sigma_Tot[k] = Arrays.copyOf(Sigma_Tot[k], nE[k]);
                Sigma_Scat[k] = Arrays.copyOf(Sigma_Scat[k], nE[k]);
                Sigma_Abs[k] = Arrays.copyOf(Sigma_Abs[k], nE[k]);
                System.out.println(k + "  " + Energy_in[k].length + "  " + Sigma_Tot[k].length + "  " + Sigma_Tot[k].length + "  " + Sigma_Scat[k].length + "  " + Sigma_Abs[k].length);
            }

        }   // material selected
    }//GEN-LAST:event_Material_MatrixActionPerformed

    private void clearDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearDataBtnActionPerformed
        jProgressBar1.setValue(0);
        CancelFlag = false;
        ClearFlag = true;
        ProcessFlag = false;
        ContinueFlag = false;
        xyCollisionSerie.clear();
        xyCapturedSerie.clear();
        xyLeakageSerie.clear();
        xyFirstCollisionSerie.clear();
        Batch_Size_total = 0;
        CollisionNumber = 0;
        FirstCollisionNumber = 0;
        LeakageNumber = 0;
        Start_I = 0;
        FirstNeutronFlag = true;
    }//GEN-LAST:event_clearDataBtnActionPerformed

    private void ExitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitBtnActionPerformed
        Runtime.getRuntime().exit(0);
    }//GEN-LAST:event_ExitBtnActionPerformed

    private void ContinueBtnPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ContinueBtnPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_ContinueBtnPropertyChange

    @SuppressWarnings("empty-statement")
    private void ContinueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContinueBtnActionPerformed
        continue_counter++;
        ContinueFlag = true;
        CancelFlag = false;
        ContinueBtn.setEnabled(false);
        ProcessBtn.setEnabled(false);
        clearDataBtn.setEnabled(false);
        jProgressBar1.setValue(0);
        try {
            Batch_Size = Integer.parseInt(this.batch_size.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Bad sample size", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        new Thread(this::MCcreateScatterPlot).start();
    }//GEN-LAST:event_ContinueBtnActionPerformed

    private void BreakBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BreakBtnActionPerformed
        CancelFlag = true;
        ProcessFlag = false;
        ContinueFlag = false;
        System.out.println("Simulation was interrupted! ");
        ProcessBtn.setEnabled(true);
        clearDataBtn.setEnabled(true);
        Batch_Size_total = Batch_Size;
        jProgressBar1.setValue(0);
    }//GEN-LAST:event_BreakBtnActionPerformed

    private void ProcessBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcessBtnActionPerformed
        if (!Material_Selected) {
            showMessage();
            return;
        }
        continue_counter = 0;
        ProcessFlag = true;
        CancelFlag = false;
        ContinueBtn.setEnabled(false);
        ProcessBtn.setEnabled(false);
        clearDataBtn.setEnabled(false);
        Batch_Size_total = Batch_Size;
        CollisionNumber = 0;
        FirstCollisionNumber = 0;
        FirstNeutronLeakageNumber = 0;
        LeakageNumber = 0;
        NeutronEnergyTot = 0.;
        NeutronLifeTimeTotal = 0.;
        NeutronTrackLengthTotal = 0.;
        NeutronSpeedTotal = 0.;
        Start_I = 0;
        jProgressBar1.setValue(0);
        xyCollisionSerie.clear();
        xyFirstCollisionSerie.clear();
        xyLeakageSerie.clear();
        xyCapturedSerie.clear();
        Slab_Thickness = 0.0;
        Slab_Length = 0.0;

        try {
            Slab_Thickness = Double.parseDouble(this.SlabThickness.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bad Slab thickness value", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Slab_Length = Double.parseDouble(this.SlabLength.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bad Slab Length value", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            IncidentNeutronEnergy = Double.parseDouble(this.Incident_Neutron_Energy.getText()) * 1.E6;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bad neutron energy value", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Batch_Size = Integer.parseInt(this.batch_size.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Bad sample size", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new Thread(this::MCcreateScatterPlot).start();
    }//GEN-LAST:event_ProcessBtnActionPerformed

    private void SlabThicknessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SlabThicknessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SlabThicknessActionPerformed

    public void showMessage() {
        JOptionPane.showMessageDialog(null, "You need to choose material first");
    }

    public void CubicSplineFast(Double[] xE, Double[] yS) {
        this.nPoints = xE.length;
        this.xE = new double[nPoints];
        this.yS = new double[nPoints];
        this.d2ydx2 = new double[nPoints];
        for (int i = 0; i < this.nPoints; i++) {
            this.xE[i] = xE[i];
            this.yS[i] = yS[i];
        }
        this.calcDeriv();
    }
    //  Calculates the second derivatives of the tabulated function
    //  for use by the cubic spline interpolation method (.interpolate)
    //  This method follows the procedure in Numerical Methods C language procedure for calculating second derivatives

    public void calcDeriv() {
        double p = 0.0D, qn = 0.0D, sig = 0.0D, un = 0.0D;
        double[] u = new double[nPoints];

        d2ydx2[0] = u[0] = 0.0;
        for (int i = 1; i <= this.nPoints - 2; i++) {
            sig = (this.xE[i] - this.xE[i - 1]) / (this.xE[i + 1] - this.xE[i - 1]);
            p = sig * this.d2ydx2[i - 1] + 2.0;
            this.d2ydx2[i] = (sig - 1.0) / p;
            u[i] = (this.yS[i + 1] - this.yS[i]) / (this.xE[i + 1] - this.xE[i]) - (this.yS[i] - this.yS[i - 1]) / (this.xE[i] - this.xE[i - 1]);
            u[i] = (6.0 * u[i] / (this.xE[i + 1] - this.xE[i - 1]) - sig * u[i - 1]) / p;
        }

        qn = un = 0.0;
        this.d2ydx2[this.nPoints - 1] = (un - qn * u[this.nPoints - 2]) / (qn * this.d2ydx2[this.nPoints - 2] + 1.0);
        for (int k = this.nPoints - 2; k >= 0; k--) {
            this.d2ydx2[k] = this.d2ydx2[k] * this.d2ydx2[k + 1] + u[k];
        }
    }

    //  INTERPOLATE
    //  Returns an interpolated value of y for a value of x from a tabulated function y=f(x)
    //  after the data has been entered via a constructor.
    //  The derivatives are calculated, bt calcDeriv(), on the first call to this method ands are
    //  then stored for use on all subsequent calls
    public double interpolate(double xx) {

        double h = 0.0D, b = 0.0D, a = 0.0D, yy = 0.0D;
        int k = 0;
        int klo = 0;
        int khi = this.nPoints - 1;
        while (khi - klo > 1) {
            k = (khi + klo) >> 1;
            if (this.xE[k] > xx) {
                khi = k;
            } else {
                klo = k;
            }
        }
        h = this.xE[khi] - this.xE[klo];

        if (h == 0.0) {
            throw new IllegalArgumentException("Two values of x are identical: point " + klo + " (" + this.xE[klo] + ") and point " + khi + " (" + this.xE[khi] + ")");
        } else {
            a = (this.xE[khi] - xx) / h;
            b = (xx - this.xE[klo]) / h;
            yy = a * this.yS[klo] + b * this.yS[khi] + ((a * a * a - a) * this.d2ydx2[klo] + (b * b * b - b) * this.d2ydx2[khi]) * (h * h) / 6.0;
        }
        return yy;
    }

    public XYDataset MonteCarlo() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        Random generator = new Random();
        jProgressBar1.setValue(0);
        if (!ContinueFlag) {
            Batch_Size_total = Batch_Size;
            Start_I = 0;
        } else {
            Batch_Size_total = Batch_Size_total + Batch_Size;
        }
        Batch_Size_total = Batch_Size_total + 1;
        MaterialAtomDensity = Density[Material_ID - 1] * Avogadro / Molar_mass[Material_ID - 1];
        for (int k = 0; k < myIsotopesList.size(); k++) {
            ComponentAtomDensity[k] = MaterialAtomDensity * Isotope_coef[k] * 1.E-24;
            SigmaMicroIsotopeTotal[k] = 0.;
            SigmaMacroIsotopeTotal[k] = 0.;
        }
        if (!ContinueFlag) {
            TotalCollisionNumber = 0;
            TotalCaptureNumber = 0;
            FirstCollisionNumber = 0;
            FirstNeutronLeakageNumber = 0;
            LeakageNumber = 0;
            NeutronKilledEnergyCutoff = 0;
            NeutronEnergyTot = 0.;
            NeutronLifeTimeTotal = 0;
            NeutronSpeedTotal = 0.;
            NeutronTrackLengthTotal = 0.;
        }
        startTime = System.currentTimeMillis();     // timer start
// begining of main loop
        SigmaMacroMaterialTotal = 0.0;
        for (long NeutronHistory = Start_I + 1; NeutronHistory < Batch_Size_total; NeutronHistory++) {
            FirstNeutronFlag = true;
            CollisionNumber = 0;
            CaptureNumber = 0;
            NeutronEnergyIn = IncidentNeutronEnergy;
            x = 0.;
            y = 0.;
            z = 0.;           // neutron starts its history at (0,0)
            u = 0.;
            v = 0.;
            w = 1.;           // neutron moves fellowing z axis
            xyCollisionSerie.add(z, y);
            do {                    // loop when neutron energy is greater than Cutoff
                SigmaMacroMaterialTotal = 0.0;
                for (int k = 0; k < myIsotopesList.size(); k++) {
// Create a CubicSpline instance and initialise it to the data stored in the arrays En, Sigma
                    CubicSplineFast(Energy_in[k], Sigma_Tot[k]);
                    SigmaMicroIsotopeTotal[k] = interpolate(NeutronEnergyIn);   // interpolation
                    SigmaMacroIsotopeTotal[k] = ComponentAtomDensity[k] * SigmaMicroIsotopeTotal[k];
                    SigmaMacroMaterialTotal = SigmaMacroMaterialTotal + SigmaMacroIsotopeTotal[k];
                    //         System.out.println(k + "   *************** Tot Interpolation at  : " + NeutronEnergyIn + " eV  " + SigmaMicroIsotopeTotal[k] + "  " + SigmaMacroMaterialTotal);
                }
                Track_length = -Math.log(generator.nextDouble()) / SigmaMacroMaterialTotal;  // random track length             
                if (!FirstNeutronFlag) {          // it's a scattered neutron
                    x = x + Track_length * u;     // random z 
                    y = y + Track_length * v;     // random y 
                    z = z + Track_length * w;     // random z 
                    xyCollisionSerie.add(z, y);
                } else {                        // it's the incident neutron
                    u = 0.;
                    v = 0.;
                    w = 1.;
                    x = 0.;
                    y = 0.;
                    z = z + Track_length;
                    xyFirstCollisionSerie.add(z, y);
                }
                if (Math.abs(z) > Slab_Thickness || Math.abs(y) > HalfSlabLength) {   // the neutron leaks
                    NeutronEnergyOut = NeutronEnergyIn;
                    if (FirstNeutronFlag) {
                        FirstNeutronLeakageNumber++;
                        LeakageNumber++;
                        LeakageFlag = true;
                        xyLeakageSerie.add(z - Track_length, y);
                        break;
                    } else {
                        LeakageNumber++;
                        LeakageFlag = true;
                        xyLeakageSerie.add(z - Track_length * w, y - Track_length * v);
                        break;
                    }
                } else {                                           //  the neutron undergoes collision
                    // sample collided nucleus 
                    double Proba = 0.;
                    for (int k = 0; k < myIsotopesList.size(); k++) {
                        Proba = Proba + SigmaMacroIsotopeTotal[k] / SigmaMacroMaterialTotal;
                        if (generator.nextDouble() <= Proba) {
                            Nuclei_Mass = Isotope_mass[Isotope_ID.indexOf(ZAID[k])];
                            CollidedNucleiID = k;
                            break;
                        }
                    }
                    CubicSplineFast(Energy_in[CollidedNucleiID], Sigma_Scat[CollidedNucleiID]);
                    SigmaMicroIsotopeScattering[CollidedNucleiID] = interpolate(NeutronEnergyIn);   // interpolation
                    // sample reaction type
                    if (generator.nextDouble() <= SigmaMicroIsotopeScattering[CollidedNucleiID] / SigmaMicroIsotopeTotal[CollidedNucleiID]) {   // scattering occured
                        // if scattering sample direction
                        phi = 2. * Math.PI * generator.nextDouble();
                        cos_phi = Math.cos(phi);
                        sin_phi = Math.sin(phi);
                        muCM = 2. * generator.nextDouble() - 1.0;
                        Double AA = Nuclei_Mass * Nuclei_Mass + 2. * Nuclei_Mass * muCM + 1.;
                        muLAB = (1. + Nuclei_Mass * muCM) / Math.sqrt(AA);
                        sin_theta_LAB = Math.sin(Math.acos(muLAB));
                        // calculate avergae neutron energy and mean life time of neutron
                        NeutronEnergyTot = NeutronEnergyTot + NeutronEnergyIn;
                        NeutronSpeed = Math.sqrt(2. * NeutronEnergyIn * 1.6E-19/ Neutron_mass);
                        NeutronSpeedTotal = NeutronSpeedTotal + NeutronSpeed;
                        NeutronTrackLengthTotal = NeutronTrackLengthTotal + Track_length;
                        NeutronLifeTimeTotal = NeutronLifeTimeTotal + Track_length * 1.E-2 / NeutronSpeed;
                        // calculate En of scattered neutron
                        NeutronEnergyIn = NeutronEnergyIn * AA / (1. + Nuclei_Mass) / (1. + Nuclei_Mass);       //  kinetics
                        // transforming the neutron direction
                        Double BB = Math.sqrt(1. - muLAB * muLAB);
                        Double CC = Math.sqrt(1. - w * w);
                        if (CC > 1.E-10) {
                            up = muLAB * u + BB * (u * w * cos_phi - v * sin_phi) / CC;
                            vp = muLAB * v + BB * (v * w * cos_phi + u * sin_phi) / CC;
                            wp = muLAB * w - BB * CC * cos_phi;
                        } else {
                            CC = Math.sqrt(1. - v * v);
                            up = muLAB * u + BB * (u * v * cos_phi - w * sin_phi) / CC;
                            vp = muLAB * v - BB * CC * cos_phi;
                            wp = muLAB * w + BB * (v * w * cos_phi - u * sin_phi) / CC;
                        }
                        //      xyCollisionSerie.add(z, y);
                        if (FirstNeutronFlag) {
                            FirstCollisionNumber++;
                            CollisionNumber++;
                            xyFirstCollisionSerie.add(z, y);
                        } else {
                            CollisionNumber++;
                            xyCollisionSerie.add(z, y);
                        }
                        FirstNeutronFlag = false;
                    } else {
                        CollisionNumber++;
                        TotalCaptureNumber++;       // neutron is captured 
                        xyCapturedSerie.add(z, y);
                        FirstNeutronFlag = true;
                        break;
                    }

                }
                if (NeutronEnergyIn < ECutOff) {
                    NeutronKilledEnergyCutoff++;
                }
                u = up;
                v = vp;
                w = wp;
            } while (NeutronEnergyIn > ECutOff);
            TotalCollisionNumber = TotalCollisionNumber + CollisionNumber;

            jProgressBar1.setValue((int) ((NeutronHistory - Start_I) * 100.0 / (Batch_Size_total - Start_I - 1)));
            if (CancelFlag) {
                break;
            }
        }   // end of main loop
        endTime = System.currentTimeMillis();       // timer stop
        elapsedTime = endTime - startTime;          // elapsed time
        if (FirstCollisionNumber != 0.) {
            AverageCollisionNumber = (double) TotalCollisionNumber / FirstCollisionNumber;
        } else {
            this.LeakageLabel.setText("Average Collision Number couldn't be calculated ");
        }
        Batch_Size_total = Batch_Size_total - 1;
        if (!CancelFlag) {
            DecimalFormat format = new DecimalFormat("######.###");
            DecimalFormat format1 = new DecimalFormat("########.###");
            DecimalFormat df = new DecimalFormat("0.000E00");
            this.FirstCollisionsLabel.setText("First Collisions Fraction :   " + format.format(FirstCollisionNumber * 100.00 / Batch_Size_total) + " %");
            this.FirstLeakageLabel.setText("First Leakage Fraction    :   " + format.format(FirstNeutronLeakageNumber * 100.00 / Batch_Size_total) + " %");
            this.LeakageLabel.setText("Total Leakage Fraction    :   " + format.format(LeakageNumber * 100.00 / Batch_Size_total) + " %");
            this.CutoffLabel.setText("Killed Cutoff Fraction    :   " + format.format((double) NeutronKilledEnergyCutoff * 100.00 / Batch_Size_total) + " %");
            this.AverageCollisionLabel.setText("Average Collision Number  :   " + format.format((float) AverageCollisionNumber));

            System.out.println("Continue_counter : " + continue_counter + "     Sample Size :  " + Batch_Size_total);
            System.out.println("First Neutron Collision number             : " + FirstCollisionNumber + "   First Collisions fraction :   " + FirstCollisionNumber * 100.0 / Batch_Size_total + " %");
            System.out.println("First Neutron Leakage number               : " + FirstNeutronLeakageNumber + "   First Leakage fraction    :   " + FirstNeutronLeakageNumber * 100.0 / Batch_Size_total + " %");
            System.out.println("Total collision number                     : " + TotalCollisionNumber + "   Average Collision Number  :    " + (double) AverageCollisionNumber);
            System.out.println("Number of neutrons killed by Energy Cutoff : " + NeutronKilledEnergyCutoff + "   Cutoff Fraction           :    " + (double) NeutronKilledEnergyCutoff * 100.0 / Batch_Size_total + " %");
            System.out.println("Leakage number                             : " + LeakageNumber + "   Leakage Fraction          :    " + LeakageNumber * 100.0 / Batch_Size_total + " %");
            System.out.println("Capture number                             : " + TotalCaptureNumber + "   Capture Fraction          :    " + TotalCaptureNumber * 100.0 / Batch_Size_total + " %");
            System.out.println("Average neutron energy                     : " + format.format((float) +  NeutronEnergyTot / TotalCollisionNumber / 1.E6) + " MeV");
            System.out.println("Neutron mean life time                     : " + df.format((double) +  NeutronLifeTimeTotal / FirstCollisionNumber ) + " s");
            System.out.println("Average neutron speed                      : " + format1.format((float) +  NeutronSpeedTotal / TotalCollisionNumber ) + " m/s");
            System.out.println("Average neutron track length               : " + format.format((float) +  NeutronTrackLengthTotal / FirstCollisionNumber ) + " m");

        }

// export series  
        dataset.addSeries(xyFirstCollisionSerie);
        dataset.addSeries(xyCollisionSerie);
        dataset.addSeries(xyLeakageSerie);
// display time            
        this.Simulation_Time.setText("Time of simulation :                  " + (double) elapsedTime / 1000 + "    seconds");
// reset flags
        ProcessFlag = false;
        ContinueFlag = false;
        CancelFlag = false;
        ContinueBtn.setEnabled(true);
        ProcessBtn.setEnabled(true);
        clearDataBtn.setEnabled(true);
        Start_I = Batch_Size_total;
        return dataset;
    }

    private ChartPanel MCcreateScatterPlot() {
        jProgressBar1.setValue(0);
        final NumberAxis domainAxis = new NumberAxis("X-Axis");
        domainAxis.setRange(0.00, Slab_Thickness);
        final NumberAxis rangeAxis = new NumberAxis("Y-Axis");
        HalfSlabLength = Slab_Length * 0.5;
        rangeAxis.setRange(-HalfSlabLength, HalfSlabLength);

        JFreeChart chart = ChartFactory.createScatterPlot("", "X", "Y", MonteCarlo(), PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();
        plot.clearDomainAxes();
        plot.clearRangeAxes();
        plot.setBackgroundPaint(new Color(220, 220, 220));
        DecimalFormat format = (DecimalFormat) DecimalFormat.getNumberInstance(Locale.ENGLISH);
        domainAxis.setNumberFormatOverride(format);
        rangeAxis.setNumberFormatOverride(format);
        domainAxis.setTickUnit(new NumberTickUnit(Slab_Thickness / 10));
        rangeAxis.setTickUnit(new NumberTickUnit(HalfSlabLength / 10));
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(rangeAxis);
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.blue);
        renderer.setSeriesPaint(2, Color.green);
        double size = 1.0;
        double delta = size / 2.0;
        Shape shape1 = new Ellipse2D.Double(-delta, -delta, size, size);
        renderer.setSeriesShape(0, shape1);
        renderer.setSeriesShape(1, shape1);
        renderer.setSeriesShape(2, shape1);
        jPanel2.setLayout(new java.awt.BorderLayout());
        ChartPanel CP = new ChartPanel(chart);
        jPanel2.add(CP, BorderLayout.CENTER);
        jPanel2.validate();
// Saving the chart as an image
/*        try {
            File imageFile = new File("MonteCarlo.png");
            ChartUtilities.saveChartAsPNG(imageFile, chart, 460, 460);
        } catch (IOException ex) {
            System.err.println(ex);
        }  */
        return new ChartPanel(chart);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 /*     try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MCcreateScatterPlot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } */
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MonteCarloScatterPlot().setVisible(true);

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AverageCollisionLabel;
    private javax.swing.JButton BreakBtn;
    private javax.swing.JButton ContinueBtn;
    private javax.swing.JLabel CutoffLabel;
    private javax.swing.JButton ExitBtn;
    private javax.swing.JLabel FirstCollisionsLabel;
    private javax.swing.JLabel FirstLeakageLabel;
    private javax.swing.JTextField Incident_Neutron_Energy;
    private javax.swing.JLabel LeakageLabel;
    private javax.swing.JComboBox<String> Material_Matrix;
    private javax.swing.JButton ProcessBtn;
    private javax.swing.JLabel Simulation_Time;
    private javax.swing.JTextField SlabLength;
    private javax.swing.JTextField SlabThickness;
    private javax.swing.JLabel TotalLeakageLabel;
    private javax.swing.JTextField batch_size;
    private javax.swing.JButton clearDataBtn;
    private javax.swing.JTextField dateTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables

}
