package deportesUnal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DataTypes.Student;
import DataStructures.*;

public class MainWindow extends JFrame implements ActionListener{
    public hashmap<Student> studentsHash;
    public Tree studentsTree;
    public Graph studentsGraph;
    public Heap studentsHeap;
    //Botones
    private JButton getStudentsButton;
    private JButton getStudentInfoButton;
    private JButton searchRelationButton;
    private JButton addStudentButton;
    private JButton deleteStudentButton;
    private JButton addSportButton;
    private JButton deleteSportButton;
    private JButton addInterestButton;
    private JButton deleteInterestButton;
    //Text Fields
    private JTextField studentNameField;
    private JTextField studentIdField;
    private JTextField sportNameField;
    private JTextField sportStudentField;
    private JTextField interestNameField;
    private JTextField interestStudentField;
    private JTextField infoStudentId;
    private JTextField infoStudentName;
    private JTextField relationStudent1Id;
    private JTextField relationStudent2Id;
    private JTextField relationShowField;
    //Text Area
    private JTextArea studentsListArea;
    private JTextArea infoStudentSportsArea;
    private JTextArea infoStudentInterestsArea;

    MainWindow(){
        //Crear estructuras
        studentsHash = new hashmap<>(8);
        studentsTree = new Tree();
        studentsHeap = new Heap();
        studentsGraph = new Graph();

        //Ventana Principal (JFrame)
        this.setTitle("Deportes Unal");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1500,1000);
        this.getContentPane().setBackground(new Color(230,230,230));
        this.setLayout(new BorderLayout());

        //PANELES
        //Panel para ver los estudiantes activos
        JPanel studentsPanel = new JPanel();
        studentsPanel.setOpaque(false);
        //studentsPanel.setBackground(Color.red);
        studentsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        studentsPanel.setLayout(new BorderLayout());

        //Panel para añadir y eliminar estudiantes
        JPanel controlPanel = new JPanel();
        controlPanel.setOpaque(false);
        //controlPanel.setBackground(Color.blue);
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        controlPanel.setLayout(new GridLayout(4,1));

        //Subpanel para manejar estudiantes
        JPanel studentControlPanel = new JPanel();
        studentControlPanel.setOpaque(false);
        studentControlPanel.setLayout(new GridLayout(1, 5));

        //Subpanel para manejar estudiantes
        JPanel sportControlPanel = new JPanel();
        sportControlPanel.setOpaque(false);
        sportControlPanel.setLayout(new GridLayout(1, 5));

        //Subpanel para manejar estudiantes
        JPanel interestControlPanel = new JPanel();
        interestControlPanel.setOpaque(false);
        interestControlPanel.setLayout(new GridLayout(1, 5));

        //Panel para mostrar relaciones entre estudiantes
        JPanel relationPanel = new JPanel();
        relationPanel.setOpaque(false);
        relationPanel.setPreferredSize(new Dimension(450, 50));
        //relationPanel.setBackground(Color.yellow);
        relationPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        relationPanel.setLayout(new BorderLayout());

        //Subpanel para ingresar los estudiantes a relacionar
        JPanel relationInputPanel = new JPanel();
        relationInputPanel.setOpaque(false);
        relationInputPanel.setLayout(new GridLayout(3, 1));

        //Subpanel para ingresar el estudiante 1 a relacionar
        JPanel relationStudent1Panel = new JPanel();
        relationStudent1Panel.setOpaque(false);
        relationStudent1Panel.setLayout(new GridLayout(1, 2));

        //Subpanel para ingresar el estudiante 2 a relacionar
        JPanel relationStudent2Panel = new JPanel();
        relationStudent2Panel.setOpaque(false);
        relationStudent2Panel.setLayout(new GridLayout(1, 2));

        //Subpanel para mostrar si hay relación o no
        JPanel relationShowPanel = new JPanel();
        relationShowPanel.setOpaque(false);
        relationShowPanel.setLayout(new GridLayout(1, 2));

        //Panel para ver toda la información de un estudiante
        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(450, 50));
        infoPanel.setOpaque((false));
        //infoPanel.setBackground(Color.green);
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        infoPanel.setLayout(new BorderLayout());

        //Subpanel para mostrar la informacion del estudiante
        JPanel infoShowPanel = new JPanel();
        infoShowPanel.setOpaque(false);
        infoShowPanel.setLayout(new GridLayout(3, 1));

        //Subpanel para ingresar el id del estudiante a buscar
        JPanel infoStudentIdPanel = new JPanel();
        infoStudentIdPanel.setOpaque(false);
        infoStudentIdPanel.setLayout(new GridLayout(1, 2));

        //Subpanel para dar el nombre del estudiante buscado
        JPanel infoStudentNamePanel = new JPanel();
        infoStudentNamePanel.setOpaque(false);
        infoStudentNamePanel.setLayout(new GridLayout(1, 2));

        //Subpanel para mostrar deportes e intereses
        JPanel infoStudentGeneralPanel = new JPanel();
        infoStudentGeneralPanel.setOpaque(false);
        infoStudentGeneralPanel.setLayout(new GridLayout(1, 2));

        //Subpanel para mostrar los deportes del estudiante
        JPanel infoStudentSportsPanel = new JPanel();
        infoStudentSportsPanel.setOpaque(false);
        infoStudentSportsPanel.setLayout(new BorderLayout());

        //Subpanel para mostrar los intereses del estudiante
        JPanel infoStudentInterestsPanel = new JPanel();
        infoStudentInterestsPanel.setOpaque(false);
        infoStudentInterestsPanel.setLayout(new BorderLayout());

        //FUENTES
        Font titleFont = new Font("sans-serif", Font.BOLD, 30);
        Font normalFont = new Font("sans-serif", Font.PLAIN, 14);

        //TITULOS
        //Titulo de infoPanel
        JLabel titleInfoPanel = new JLabel("Información del estudiante");
        titleInfoPanel.setVerticalAlignment(JLabel.CENTER);
        titleInfoPanel.setHorizontalAlignment(JLabel.CENTER);
        titleInfoPanel.setFont(titleFont);
        titleInfoPanel.setBackground(new Color(230, 230, 100));
        titleInfoPanel.setOpaque(false); 

        //Titulo de controlPanel
        JLabel titleControlPanel = new JLabel("Administrar estudiantes");
        titleControlPanel.setVerticalAlignment(JLabel.CENTER);
        titleControlPanel.setHorizontalAlignment(JLabel.CENTER);
        titleControlPanel.setFont(titleFont);
        titleControlPanel.setBounds(0, 0, 1000, 50);
        titleControlPanel.setBackground(new Color(230, 230, 100));
        titleControlPanel.setOpaque(false); 

        //Titulo de studentsPanel
        JLabel titleStudentsPanel = new JLabel("Lista de estudiantes");
        titleStudentsPanel.setVerticalAlignment(JLabel.CENTER);
        titleStudentsPanel.setHorizontalAlignment(JLabel.CENTER);
        titleStudentsPanel.setFont(titleFont);
        titleStudentsPanel.setBounds(0, 0, 1000, 50);
        titleStudentsPanel.setBackground(new Color(230, 230, 100));
        titleStudentsPanel.setOpaque(false); 

        //titulo de relationPanel
        JLabel titleRelationPanel = new JLabel("Relaciones entre estudiantes");
        titleRelationPanel.setVerticalAlignment(JLabel.CENTER);
        titleRelationPanel.setHorizontalAlignment(JLabel.CENTER);
        titleRelationPanel.setFont(titleFont);
        titleRelationPanel.setBounds(0, 0, 500, 50);
        titleRelationPanel.setBackground(new Color(230, 230, 100));
        titleRelationPanel.setOpaque(false); 

        //Labels
        //Labels del panel de control
        //Label para el control de estudiantes
        JLabel label1ControlPanel = new JLabel("Estudiante:");
        label1ControlPanel.setVerticalAlignment(JLabel.CENTER);
        label1ControlPanel.setHorizontalAlignment(JLabel.CENTER);
        label1ControlPanel.setFont(normalFont);
        label1ControlPanel.setOpaque(false);

        //Label para el control de deportes
        JLabel label2ControlPanel = new JLabel("Deporte:");
        label2ControlPanel.setVerticalAlignment(JLabel.CENTER);
        label2ControlPanel.setHorizontalAlignment(JLabel.CENTER);
        label2ControlPanel.setFont(normalFont);
        label2ControlPanel.setOpaque(false);

        //Label para el control de intereses
        JLabel label3ControlPanel = new JLabel("Interés:");
        label3ControlPanel.setVerticalAlignment(JLabel.CENTER);
        label3ControlPanel.setHorizontalAlignment(JLabel.CENTER);
        label3ControlPanel.setFont(normalFont);
        label3ControlPanel.setOpaque(false);

        //Labels para el infoPanel
        //Label para el id del estudiante
        JLabel labelIdInfoPanel = new JLabel("Id:");
        labelIdInfoPanel.setFont(normalFont);
        labelIdInfoPanel.setOpaque(false);

        //Label para el nombre del estudiante
        JLabel labelNameInfoPanel = new JLabel("Nombre:");
        labelNameInfoPanel.setFont(normalFont);
        labelNameInfoPanel.setOpaque(false);

        //Label para los deportes del estudiante
        JLabel labelSportsInfoPanel = new JLabel("Deportes:");
        labelSportsInfoPanel.setFont(normalFont);
        labelSportsInfoPanel.setOpaque(false);

        //Label para los intereses del estudiante
        JLabel labelInteresesInfoPanel = new JLabel("Intereses:");
        labelInteresesInfoPanel.setFont(normalFont);
        labelInteresesInfoPanel.setOpaque(false);

        //Labels para mostrar la relación de 2 estudiantes
        //Label para ingresar el nombre del estudiante 1
        JLabel relationStudent1Label = new JLabel("Estudiante 1:");
        relationStudent1Label.setFont(normalFont);
        relationStudent1Label.setOpaque(false);

        //Label para ingresar el nombre del estudiante 2
        JLabel relationStudent2Label = new JLabel("Estudiante 2:");
        relationStudent2Label.setFont(normalFont);
        relationStudent2Label.setOpaque(false);

        //Label donde se muestra la relación entre estudiantes
        JLabel relationShowLabel = new JLabel("Relación:");
        relationShowLabel.setFont(normalFont);
        relationShowLabel.setOpaque(false);

        //Botones
        //Boton para consultar los estudiantes
        getStudentsButton = new JButton("Consultar");
        getStudentsButton.addActionListener(this);
        getStudentsButton.setFont(normalFont);

        //Boton para consultar la info de un estudiante
        getStudentInfoButton = new JButton("Consultar");
        getStudentInfoButton.addActionListener(this);
        getStudentInfoButton.setBounds(50, 60, 100, 50);
        getStudentInfoButton.setFont((normalFont));

        //Boton para hallar la relacion entre dos estudiantes
        searchRelationButton = new JButton("Buscar");
        searchRelationButton.addActionListener(this);
        searchRelationButton.setBounds(50, 60, 100, 50);
        searchRelationButton.setFont(normalFont);

        //Boton para añadir un nuevo estudiante
        addStudentButton = new JButton("Añadir");
        addStudentButton.addActionListener(this);
        addStudentButton.setFont(normalFont);

        //Boton para borrar a un estudiante
        deleteStudentButton = new JButton("Borrar");
        deleteStudentButton.addActionListener(this);
        deleteStudentButton.setFont(normalFont);

        //Boton para añadir un deporte a un estudiante
        addSportButton = new JButton("Añadir");
        addSportButton.addActionListener(this);
        addSportButton.setFont(normalFont);

        //Boton para quitar un deporte de un estudiante
        deleteSportButton = new JButton("Quitar");
        deleteSportButton.addActionListener(this);
        deleteSportButton.setFont(normalFont);

        //Boton para añadir un interés a un estudiante
        addInterestButton = new JButton("Añadir");
        addInterestButton.addActionListener(this);
        addInterestButton.setFont(normalFont);

        //Boton para eliminar un interés de un estudiante
        deleteInterestButton = new JButton("Quitar");
        deleteInterestButton.addActionListener(this);
        deleteInterestButton.setFont(normalFont);

        //Text Fields control panel
        //Text field para el nombre del estudiante
        studentNameField = new JTextField();
        studentNameField.setText("Nombre");
        studentNameField.setFont(normalFont);

        //Text field para el id del estudiante
        studentIdField = new JTextField();
        studentIdField.setText("Id");
        studentIdField.setFont(normalFont);

        //Text field para el nombre del deporte
        sportNameField = new JTextField();
        sportNameField.setText("Deporte");

        //Text field para el id del estudiante asociado al deporte
        sportStudentField = new JTextField();
        sportStudentField.setText("Id estudiante");

        //Text field para el nombre del interés
        interestNameField = new JTextField();
        interestNameField.setText("Interés");

        //Text field para el id del estudiante asociado
        interestStudentField = new JTextField();
        interestStudentField.setText("Id estdiante");

        //Texts fields del info del estudiante
        //Text field para el id
        infoStudentId = new JTextField(); 
        infoStudentId.setText("Id");

        //Text field para el nombre
        infoStudentName = new JTextField();
        infoStudentName.setText("");
        infoStudentName.setEditable(false);

        //Text fields para el panel de relación
        //Text field para ingresar el id del estudiante 1
        relationStudent1Id = new JTextField();
        relationStudent1Id.setText("Id estudiante 1");

        //Text field para ingresar el id del estudiante 2
        relationStudent2Id = new JTextField();
        relationStudent2Id.setText("Id estudiante 2");

        //Text field para mostrar el tipo de relacion que hay entre los estudiantes
        relationShowField = new JTextField();
        relationShowField.setEditable(false);

        //Text Areas
        //Text area para la lista de estudiantes
        studentsListArea = new JTextArea();
        studentsListArea.setFont(normalFont);
        studentsListArea.setEditable(false);
        JScrollPane scrollStudentsList = new JScrollPane(studentsListArea);

        //Text Area para mostrar los deportes del estudiantes
        infoStudentSportsArea = new JTextArea();
        infoStudentSportsArea.setFont(normalFont);
        infoStudentSportsArea.setEditable(false);
        JScrollPane scrollSportsList = new JScrollPane(infoStudentSportsArea);

        //Text Area para mostrar los intereses del estudiante
        infoStudentInterestsArea = new JTextArea();
        infoStudentInterestsArea.setFont(normalFont);
        infoStudentInterestsArea.setEditable(false);
        JScrollPane scrollInterestsList = new JScrollPane(infoStudentInterestsArea);
        
        //Añadiendo a infoPanel
        infoPanel.add(titleInfoPanel, BorderLayout.NORTH);
        infoStudentIdPanel.add(labelIdInfoPanel);
        infoStudentIdPanel.add(infoStudentId);
        infoShowPanel.add(infoStudentIdPanel);
        infoStudentNamePanel.add(labelNameInfoPanel);
        infoStudentNamePanel.add(infoStudentName);
        infoShowPanel.add(infoStudentNamePanel);
        infoStudentSportsPanel.add(labelSportsInfoPanel, BorderLayout.NORTH);
        infoStudentSportsPanel.add(scrollSportsList, BorderLayout.CENTER);
        infoStudentGeneralPanel.add(infoStudentSportsPanel);
        infoStudentInterestsPanel.add(labelInteresesInfoPanel, BorderLayout.NORTH);
        infoStudentInterestsPanel.add(scrollInterestsList, BorderLayout.CENTER);
        infoStudentGeneralPanel.add(infoStudentInterestsPanel);
        infoShowPanel.add(infoStudentGeneralPanel);
        infoPanel.add(infoShowPanel, BorderLayout.CENTER);
        infoPanel.add(getStudentInfoButton, BorderLayout.SOUTH);

        //Añadiendo a controPanel
        controlPanel.add(titleControlPanel);
        studentControlPanel.add(label1ControlPanel);
        studentControlPanel.add(studentNameField);
        studentControlPanel.add(studentIdField);
        studentControlPanel.add(addStudentButton);
        studentControlPanel.add(deleteStudentButton);
        controlPanel.add(studentControlPanel);
        sportControlPanel.add(label2ControlPanel);
        sportControlPanel.add(sportNameField);
        sportControlPanel.add(sportStudentField);
        sportControlPanel.add(addSportButton);
        sportControlPanel.add(deleteSportButton);
        controlPanel.add(sportControlPanel);
        interestControlPanel.add(label3ControlPanel);
        interestControlPanel.add(interestNameField);
        interestControlPanel.add(interestStudentField);
        interestControlPanel.add(addInterestButton);
        interestControlPanel.add(deleteInterestButton);
        controlPanel.add(interestControlPanel);

        //Añadiendo a studentsPanel
        studentsPanel.add(titleStudentsPanel, BorderLayout.NORTH);
        studentsPanel.add(scrollStudentsList, BorderLayout.CENTER);
        studentsPanel.add(getStudentsButton, BorderLayout.SOUTH);

        //Añadiendo a relationPanel
        relationPanel.add(titleRelationPanel, BorderLayout.NORTH);
        relationStudent1Panel.add(relationStudent1Label);
        relationStudent1Panel.add(relationStudent1Id);
        relationInputPanel.add(relationStudent1Panel);
        relationStudent2Panel.add(relationStudent2Label);
        relationStudent2Panel.add(relationStudent2Id);
        relationInputPanel.add(relationStudent2Panel);
        relationShowPanel.add(relationShowLabel);
        relationShowPanel.add(relationShowField);
        relationInputPanel.add(relationShowPanel);
        relationPanel.add(relationInputPanel, BorderLayout.CENTER);
        relationPanel.add(searchRelationButton, BorderLayout.SOUTH);

        //Añadiendo paneles
        this.add(studentsPanel, BorderLayout.CENTER);
        this.add(infoPanel, BorderLayout.EAST);
        this.add(controlPanel, BorderLayout.SOUTH);
        this.add(relationPanel, BorderLayout.WEST);

        //Mostrar la ventana
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == getStudentsButton){
            //Implementar la consulta de los estudiantes
        }else if(e.getSource() == getStudentInfoButton){
            //Obtener la info de un estudiante
            String studentIdString = infoStudentId.getText();
            if (studentIdString == ""){
                return;
            }
            int studentId;
            try{
                studentId = Integer.parseInt(studentIdString);
            }catch(Exception ex){
                return;
            }
            Student currStudent = studentsHash.getStudent(studentId);
            infoStudentName.setText(currStudent.getName());
            String sportsString = "";
            String interestsString = "";
            for (String currSport: currStudent.getSports()){{
                sportsString = sportsString + currSport + '\n';
            }}
            for (String currInterest: currStudent.getInterest()){
                interestsString = interestsString + currInterest + '\n';
            }
            infoStudentSportsArea.setText(sportsString);;
            infoStudentInterestsArea.setText(interestsString);
        }else if(e.getSource() == searchRelationButton){
            //Implementar la función para ver si hay una relacion entre estudiantes
        }else if(e.getSource() == addStudentButton){
            //Añadir un estudiante
            String studentName = studentNameField.getText();
            String studentIdString = studentIdField.getText();
            if(studentName == "" || studentIdString == ""){
                return;
            }
            int studentId;
            try{
                studentId = Integer.parseInt(studentIdString);
            }catch(Exception exception){
                return;
            }
            Student newStudent = new Student(studentId, studentName);
            studentsHeap.insert(newStudent);
            studentsGraph.addVertex(newStudent);
            studentsHash.insertStudent(newStudent);
        }else if(e.getSource() == deleteStudentButton){
            //Implementar borrar a un estudiante
        }else if(e.getSource() == addSportButton){
            //Implemetar agregar un deporte a un estudiante
        }else if(e.getSource() == deleteSportButton){
            //Implementar borrar un deporte de un estudiante
        }else if(e.getSource() == addInterestButton){
            //Implementar agregar un interés a un estudiante
        }else if(e.getSource() == deleteInterestButton){
            //Implemetar eliminar un interés de un estudiante
        }
    }
}
