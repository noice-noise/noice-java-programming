
package tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import static tictactoe.Theme.*;
import static java.awt.Font.*;
import static java.awt.Label.*;

public class TicTacToeUI {
    StartMenu startMenu;
    public String version = "v1";

    public TicTacToeUI(String frameName) {
        startMenu = new StartMenu(frameName);
    }

}


class StartMenu {

    private String frameName;
    private Frame entryFrame;
    private URL filePath;
    private ImageIcon icon;

    private Button[] btnThemes;
    private Label lblTheme;

    private Panel pnlMain;

    private Panel pnlUpper;
    private Panel pnlCenter;
    private Panel pnlLower;
    private Panel pnlLowerButtons;
    private Panel pnlThemes;

    private Label lblWelcome;
    private Label lblp1;
    private TextField txtp1Name;
    private TextField txtp1Symbol;

    private Label lblp2;
    private TextField txtp2Name;
    private TextField txtp2Symbol;

    private Button btnStart;
    private Button btnHowToPlay;
    private Button btnAbout;
    private Button btnQuit;

    private Player playerOne;
    private Player playerTwo;

    private Label message;


    public StartMenu(String frameName) {
        this.frameName = frameName;
        entryFrame = new Frame();

        setRandomThemeID();
        initialize();
    }

    public StartMenu(String frameName, int themeID) {
        this.frameName = frameName;
        entryFrame = new Frame();
        initialize();
    }

    private void initialize(){
        getFilePath();

        initializeView();
        initializeWelcomeLabel();
        initializePlayerEntries();
        initializeMenuButtons();
        initializeThemeButtons();
        createListeners();
        initializeTheme();
        initializeFrame();
    }

    private void initializeFrame() {
        entryFrame.setTitle(frameName);
        entryFrame.setSize(300,300);
        entryFrame.setResizable(false);
        entryFrame.setLocationRelativeTo(null);
        entryFrame.setVisible(true);
        entryFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void initializeView() { ;
        icon = new ImageIcon(filePath);
        entryFrame.setIconImage(icon.getImage());
        message = new Label("");

        pnlMain = new Panel(new GridLayout(3,1));
        pnlUpper = new Panel(new GridLayout(1,1,3,3));
        pnlCenter = new Panel();
        pnlLower = new Panel(new GridLayout(2,1,3,0));
        pnlLowerButtons = new Panel(new FlowLayout());
        pnlThemes = new Panel(new FlowLayout());

        pnlMain.add(pnlUpper);
        pnlMain.add(pnlCenter);
        pnlMain.add(pnlLower);
        entryFrame.add(pnlMain);
    }

    private void initializeWelcomeLabel(){
        lblWelcome = new Label("TicTacToe!");
        pnlUpper.add(lblWelcome);
    }

    private void initializePlayerEntries() {
        lblp1 = new Label("PLAYER 1: ");
        txtp1Name = new TextField(10);
        txtp1Name.setText("Player One");
        txtp1Name.setForeground(Color.GRAY);
        txtp1Symbol = new TextField(5);
        txtp1Symbol.setText("X");
        txtp1Symbol.setForeground(Color.GRAY);

        lblp2 = new Label("PLAYER 2: ");
        txtp2Name = new TextField(10);
        txtp2Name.setText("Player Two");
        txtp2Name.setForeground(Color.GRAY);
        txtp2Symbol = new TextField(5);
        txtp2Symbol.setText("O");
        txtp2Symbol.setForeground(Color.GRAY);

        pnlCenter.add(lblp1);
        pnlCenter.add(txtp1Name);
        pnlCenter.add(txtp1Symbol);
        pnlCenter.add(lblp2);
        pnlCenter.add(txtp2Name);
        pnlCenter.add(txtp2Symbol);
    }

    private void initializeMenuButtons(){
        btnStart = new Button("START");
        btnHowToPlay = new Button("How To Play");
        btnAbout = new Button("About");
        btnQuit = new Button("Quit");
        pnlLowerButtons.add(btnStart);

        pnlLowerButtons.add(btnHowToPlay);
        pnlLowerButtons.add(btnAbout);
        pnlLowerButtons.add((btnQuit));
        pnlLower.add(pnlLowerButtons);

    }

    private void initializeThemeButtons(){
        btnThemes = new Button[MAX_NO_OF_THEMES];
        btnThemes[0] = new Button("O");
        btnThemes[1] = new Button("V");
        btnThemes[2] = new Button("B");
        lblTheme = new Label("Themes:");

        pnlThemes.add(lblTheme);
        pnlThemes.add(btnThemes[0]);
        pnlThemes.add(btnThemes[1]);
        pnlThemes.add(btnThemes[2]);
        pnlLower.add(pnlThemes);
        setThemeButtons();
    }

    private void createListeners() {
        btnStart.addActionListener(new HandleButton());
        btnHowToPlay.addActionListener(new HandleButton());
        btnAbout.addActionListener(new HandleButton());
        btnQuit.addActionListener(new HandleButton());

        txtp1Name.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                txtp1Name.setText("");
            }
        });

        txtp1Name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        txtp2Name.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                txtp2Name.setText("");
            }
        });

        txtp2Name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        txtp1Symbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                txtp1Symbol.setText("");
            }
        });

        txtp1Symbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        txtp2Symbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                txtp2Symbol.setText("");
            }
        });

        txtp2Symbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        createThemeButtonListeners();
    }

    private void createThemeButtonListeners() {
        for (int themeNum = 0; themeNum < MAX_NO_OF_THEMES; themeNum++) {
            int finalThemeNum = themeNum;
            btnThemes[themeNum].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setThemeWithID(finalThemeNum);
                    System.out.println("ThemeID: " + themeID);
                    initializeTheme();
                }
            });
        }
    }


    class HandleButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("START")){
                try{
                    validateInputs();
                    setPlayers();
                    entryFrame.dispose();
                    new GameFrame("TicTacToe!", playerOne, playerTwo);
                } catch (Exception ex) {
                    System.out.println(ex.getClass());
                    setMessage(ex.getMessage());
                    JOptionPane.showMessageDialog(entryFrame, message, "Input Error",JOptionPane.WARNING_MESSAGE);
                }
            }
            else if (e.getActionCommand().equals("How To Play")){
                setMessage("Two Players will take turn taking fields until the game is over.");
                JOptionPane.showMessageDialog(entryFrame, message,"How To Play",JOptionPane.PLAIN_MESSAGE);
            }
            else if (e.getActionCommand().equals("About")){
                setMessage("Coded by: COMEROS, Raul, Jr.");
                JOptionPane.showMessageDialog(entryFrame, message,"About",JOptionPane.PLAIN_MESSAGE);
            }
            else if (e.getActionCommand().equals("Quit")){
                setMessage("Are you sure you want to quit?");
                int confirm = JOptionPane.showConfirmDialog(entryFrame, message, "Quit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){
                    entryFrame.dispose();
                }
            }
        }

    }
    private void validateInputs(){

        if(isEmptyPlayerInput()){
            throw new IllegalStateException("Please enter Player name.");
        }

        if (isEmptySymbolInput()){
            throw new IllegalStateException("Please enter Player Symbol.");
        }

        if (txtp1Name.getText().equals(txtp2Name.getText())){
            throw new IllegalArgumentException("Player names are similar.");
        }

        if ((txtp1Symbol.getText().length() > 1) || (txtp2Symbol.getText().length() > 1)) {
            throw new IllegalArgumentException("Player symbols must be a character.");
        }

        if (txtp1Symbol.getText().equals(txtp2Symbol.getText())){
            throw new IllegalArgumentException("Player symbols are similar.");
        }

        System.out.println("All inputs are valid...");
    }

    private void setPlayers(){
        setPlayers(1, new Player(txtp1Name.getText(), txtp1Symbol.getText().charAt(0)));
        setPlayers(2, new Player(txtp2Name.getText(), txtp2Symbol.getText().charAt(0)));
    }

    private boolean isEmptyPlayerInput() {
        if((txtp1Name.getText().length() == 0) || (txtp2Name.getText().length() == 0)){
            return true;
        }
        return false;
    }

    private boolean isEmptySymbolInput(){
        if ((txtp1Symbol.getText().length() == 0) || (txtp2Symbol.getText().length() == 0)){
            return true;
        }
        return false;
    }

    private void setPlayers(int playerNumber, Player info){
        try {
            if (playerNumber == 1)
                playerOne = new Player(info.getName(), info.getSymbol());
            else if (playerNumber == 2)
                playerTwo = new Player(info.getName(), info.getSymbol());
        } catch (Exception e){
            throw new  IllegalArgumentException("Invalid player inputs, please try again.");
        }
    }


    private void initializeTheme(){
        try{
            setComponentDesign();
            setPanelDesigns();
        } catch (Exception e){
            throw e;
        }
    }

    private void setComponentDesign(){
        lblWelcome.setFont(new Font("Helvetica", BOLD, 40));
        lblWelcome.setAlignment(CENTER);
        lblWelcome.setForeground(textColor);
        lblWelcome.setBackground(background);

        lblp1.setFont(textFont);
        lblp1.setAlignment(CENTER);
        lblp1.setForeground(textColor);
        lblp1.setBackground(background);
        lblp2.setFont(textFont);
        lblp2.setAlignment(CENTER);
        lblp2.setForeground(textColor);
        lblp2.setBackground(background);

        txtp1Symbol.setFont(textFont);
        txtp2Symbol.setFont(textFont);
        txtp1Name.setFont(textFont);
        txtp2Name.setFont(textFont);

        btnStart.setForeground(background);
        btnStart.setBackground(textColor);

        btnHowToPlay.setForeground(background);
        btnHowToPlay.setBackground(textColor);

        btnAbout.setForeground(background);
        btnAbout.setBackground(textColor);

        btnQuit.setForeground(background);
        btnQuit.setBackground(textColor);

        lblTheme.setForeground(foreground);
        lblTheme.setBackground(background);

        message.setFont(textFont);
        message.setForeground(foreground);
        message.setBackground(background);
        message.setAlignment(CENTER);
    }

    private void setPanelDesigns(){
        pnlUpper.setBackground(background);
        pnlCenter.setBackground(background);
        pnlLower.setBackground(background);
        pnlLowerButtons.setBackground(background);
        pnlThemes.setBackground(background);
    }

    private void setThemeButtons(){
        btnThemes[0].setForeground(new Color(255, 135, 17));
        btnThemes[0].setBackground(new Color(38, 19, 0));

        btnThemes[1].setForeground(new Color(226, 109, 255));
        btnThemes[1].setBackground(new Color(42, 0, 51));

        btnThemes[2].setForeground(new Color(0, 202, 253));
        btnThemes[2].setBackground(new Color(0, 29, 38));
    }

    private void setMessage(String str) {
        message.setText(str);
    }

    private void getFilePath(){
        filePath = getClass().getResource("tictactoe.png");
    }
}


class GameFrame{
    private String frameName;
    private URL filePath;
    private ImageIcon icon;
    final static int MAX_DIMENSION = 3;
    private Player playerOne;
    private int scorePlayerOne;
    private int scorePlayerTwo;
    private Player playerTwo;

    private Frame frame;
    private TicTacToe ticTacToe;

    private Panel pnlGameInfo;
    private Label lblPlayerOne;
    private Label lblScorePlayerOne;
    private Label lblPlayerTwo;
    private Label lblScorePlayerTwo;

    private Panel pnlInGameMenu;
    private Button btnReset;
    private Button btnNewGame;
    private Button btnQuit;

    private Panel pnlField;
    private Button[][] tiles;

    private boolean scoresUpdated = false;
    private Panel pnlGameInfoUpper;
    private Panel pnlGameInfoLower;
    private Label lblGameStatusInfo;

    private Label message;

    public GameFrame(String frameName, Player playerOne, Player playerTwo) {
        this.frameName = frameName;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        initialize();
    }

    private void initialize() {
        getFilePath();
        initializeSetup();
        initializeBoardFrame();
        initializeBoardButtons();
        initializeFieldPanel();
        initializeComponents();
        initializeGameInfo();
        initializeInGameMenuListeners();
        addAllPanelsToFrame();
        initializeFieldListeners();
        initializeTheme();
    }

    private void updateComponents(int row, int col){
        try{
            updateFieldWith(row, col);
            checkGameConclusion();
        } catch (IllegalCallerException ec){
            setMessage(" " + ec.getLocalizedMessage());
            JOptionPane.showMessageDialog(frame, message, "Position Occupied", JOptionPane.PLAIN_MESSAGE);
        } catch (IllegalArgumentException | IllegalStateException ae){
            setMessage(ae.getLocalizedMessage() + "\n Please press Reset or New Game.");
            JOptionPane.showMessageDialog(frame, message, "Round Complete", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void setMessage(String str) {
       message.setText(str);
    }

    private void initializeSetup() {
        if (!isScoresUpdated()){
            this.scorePlayerOne = 0;
            this.scorePlayerTwo = 0;
        }
        ticTacToe = new TicTacToe(playerOne, playerTwo);
    }

    private void initializeBoardFrame() {
        frame = new JFrame();
        frame.setTitle(frameName);
        frame.setSize(375,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        icon = new ImageIcon(filePath);
        frame.setIconImage(icon.getImage());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private void initializeBoardButtons() {
        tiles = new Button[MAX_DIMENSION][MAX_DIMENSION];
    }

    private void initializeComponents(){
        btnNewGame = new Button("New Game");
        btnReset = new Button("Reset Round");
        btnQuit = new Button("Quit");
        message = new Label();
        lblPlayerOne = new Label("  " + playerOne.getName());
        lblPlayerTwo = new Label(playerTwo.getName());
        lblScorePlayerOne = new Label("" + scorePlayerOne);
        lblScorePlayerTwo = new Label("" + scorePlayerTwo);
        pnlGameInfo = new Panel();
        pnlGameInfoUpper = new Panel(new FlowLayout());
        pnlGameInfoLower = new Panel(new FlowLayout());
        lblGameStatusInfo = new Label();
        pnlInGameMenu = new Panel(new FlowLayout());
    }

    private void initializeGameInfo(){
        pnlGameInfoUpper.add(lblPlayerOne, GroupLayout.Alignment.CENTER);
        pnlGameInfoUpper.add(lblScorePlayerOne, GroupLayout.Alignment.CENTER);
        pnlGameInfoUpper.add(lblScorePlayerTwo, GroupLayout.Alignment.CENTER);
        pnlGameInfoUpper.add(lblPlayerTwo, GroupLayout.Alignment.CENTER);
        pnlGameInfo.add(pnlGameInfoUpper);
        pnlInGameMenu.add(btnReset);
        pnlInGameMenu.add(btnNewGame);
        pnlInGameMenu.add(btnQuit);
    }

    private void initializeInGameMenuListeners(){
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Reset Round pressed...");
                frame.dispose();
                initialize();
            }
        });

        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StartMenu("TicTacToe!", themeID);
            }
        });

        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMessage("Are you sure you want to quit?");
                int confirm = JOptionPane.showConfirmDialog(frame, message, "Quit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION){
                    frame.dispose();
                }
            }
        });
    }

    private void initializeFieldPanel(){
        pnlField = new Panel(new GridLayout(3,3,3,3));
        initializeAndAddTilesToPanel();
    }

    public void initializeAndAddTilesToPanel(){
        Button blankButton;
        for (int row = 0; row < MAX_DIMENSION; row++) {
            for (int col = 0; col < MAX_DIMENSION; col++) {
                blankButton = new Button(" ");
                blankButton.setFont(new Font("Arial",1,50));
                tiles[row][col] = blankButton;
                pnlField.add(tiles[row][col]);
            }
        }
    }

    private void addAllPanelsToFrame() {
        frame.add(pnlGameInfo, BorderLayout.NORTH);
        frame.add(pnlField, BorderLayout.CENTER);
        frame.add(pnlInGameMenu, BorderLayout.SOUTH);
    }

    private void initializeFieldListeners(){
        for (int row = 0; row < MAX_DIMENSION; row++) {
            for (int col = 0; col < MAX_DIMENSION; col++) {
                tiles[row][col].addActionListener(new FieldActionHandler(row+1,  col+1));
            }
        }
    }

    class FieldActionHandler implements ActionListener {

        protected int row;
        protected int col;
        public FieldActionHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            updateComponents(row, col);
        }

    }

    private void updatePlayerScores(Player player){
        setScoresUpdated(true);
        if (player==playerOne){
            scorePlayerOne++;
        }
        else if (player==playerTwo){
            scorePlayerTwo++;
        }
        updatePlayerScoresInfo();
    }

    private void updateFieldWithOwner(int row, int col, Player playerOwner){
        tiles[row][col].setFont(new Font("Arial",1,50));
        tiles[row][col].setLabel(Character.toString(playerOwner.getSymbol()));
        tiles[row][col].setFont(new Font("Arial",1,50));
    }

    private void updateFieldWith(int row, int col){
        Player currentPlayer = ticTacToe.getCurrentPlayer();
        ticTacToe.setRoundWithPosition(row, col);
        updateFieldWithOwner(row-1, col-1, currentPlayer);
        updateTurnDisplay(currentPlayer);
    }

    private void updateTurnDisplay(Player currentPlayer) {
        if (currentPlayer == playerOne){
            lblPlayerOne.setFont(textFont_BOLD);
            lblPlayerTwo.setFont(textFont);
        }
        else if (currentPlayer == playerTwo){
            lblPlayerTwo.setFont(textFont_BOLD);
            lblPlayerOne.setFont(textFont);
        }
    }

    private void checkGameConclusion(){
        ticTacToe.checkWinAndUpdateStatus();
        hasWinner();
        isDraw();
    }

    private void hasWinner(){
        if(ticTacToe.hasWinner()){
            updatePlayerScores(ticTacToe.getPlayerWinner());
            displayRoundConclusion("WINNER! " + ticTacToe.getPlayerWinner().getName());
//            System.out.println("WINNER! " + ticTacToe.getPlayerWinner().getName());
        }
    }

    private void isDraw(){
        if(ticTacToe.isDraw()){
            displayRoundConclusion("DRAW!");
            System.out.println("DRAW!");
        }
    }

    private void setScoresUpdated(boolean status){
        scoresUpdated = true;
    }

    private boolean isScoresUpdated(){
        return scoresUpdated;
    }

    private void updatePlayerScoresInfo(){
        lblScorePlayerOne.setText("" + scorePlayerOne);
        lblScorePlayerTwo.setText("" + scorePlayerTwo);
    }

    private void displayRoundConclusion(String info){
        lblGameStatusInfo.setText(info);
        lblGameStatusInfo.setAlignment(CENTER);
        Object[] options = { "Continue next Round", "New Game", "Cancel" };
        int selectedValue = JOptionPane.showOptionDialog(frame, lblGameStatusInfo, "Round Complete", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (selectedValue == 0){
            frame.dispose();
            initialize();
        } else if (selectedValue == 1){
            frame.dispose();
            new StartMenu("TicTacToe!");
        }
        System.out.println("Selected value: " + selectedValue);
    }

    private void initializeTheme() {
        setTheme();
    }

    private void setTheme(){
        setGameInfoTheme();
        setFieldTheme();
        setPanelTheme();
        setMenuButtonsTheme();
        setMessageTheme();
    }

    private void setMenuButtonsTheme() {
        btnReset.setForeground(foreground);
        btnReset.setBackground(background);
        btnNewGame.setForeground(foreground);
        btnNewGame.setBackground(background);
        btnQuit.setForeground(foreground);
        btnQuit.setBackground(background);
    }

    private void setPanelTheme() {
        pnlGameInfoUpper.setForeground(foreground);
        pnlGameInfoUpper.setBackground(background);
        pnlGameInfo.setForeground(foreground);
        pnlGameInfo.setBackground(background);
        pnlInGameMenu.setForeground(foreground);
        pnlInGameMenu.setBackground(background);
        pnlField.setForeground(foreground);
        pnlField.setBackground(background);
        pnlGameInfoLower.setForeground(foreground);
        pnlGameInfoLower.setBackground(background);
    }

    private void setGameInfoTheme() {
        lblGameStatusInfo.setFont(textFont);
        lblGameStatusInfo.setForeground(foreground);
        lblGameStatusInfo.setBackground(background);
        lblPlayerOne.setFont(textFont);
        lblPlayerOne.setForeground(foreground);
        lblPlayerOne.setBackground(background);
        lblPlayerTwo.setFont(textFont);
        lblPlayerTwo.setForeground(foreground);
        lblPlayerTwo.setBackground(background);
        lblScorePlayerOne.setFont(headingFont);
        lblScorePlayerOne.setForeground(foreground);
        lblScorePlayerOne.setBackground(background);
        lblScorePlayerTwo.setFont(headingFont);
        lblScorePlayerTwo.setForeground(foreground);
        lblScorePlayerTwo.setBackground(background);
    }

    private void setMessageTheme(){
        message.setFont(textFont);
        message.setForeground(foreground);
        message.setBackground(background);
        message.setAlignment(CENTER);
    }

    private void setFieldTheme() {
        for (int row = 0; row < MAX_DIMENSION; row++) {
            for (int col = 0; col < MAX_DIMENSION; col++) {
                tiles[row][col].setForeground(foreground);
                tiles[row][col].setBackground(background);
            }
        }
    }

    private void getFilePath(){
        filePath = getClass().getResource("tictactoe.png");
    }
}


class Theme {
    public final static int ORANGE = 0;
    public final static int VIOLET = 1;
    private static final int BLUE = 2;
    public static int MAX_NO_OF_THEMES = 3;

    public static int themeID;

    protected static Color textColor;
    protected static Color foreground;
    protected static Color background;

    protected static Font textFont;
    protected static Font textFont_BOLD;

    protected static Font headingFont;
    protected static int sizeNormal;
    protected static int sizeHeading;
    protected static int sizeNote;

    public static void setThemeWithID(int id){
        switch (id){
            case 0:
                themeID = ORANGE;
                setOrange();
                break;
            case 1:
                themeID = VIOLET;
                setViolet();
                break;
            case 2:
                themeID = BLUE;
                setBlue();
                break;
            default:
                throw new IllegalArgumentException("Illegal Theme ID");
        }
    }

    public static void setOrange(){
        sizeHeading = 40;
        sizeNormal = 12;
        sizeNote = 10;
        textFont = new Font("Arial", Font.PLAIN, sizeNormal);
        textFont_BOLD = new Font("Arial", BOLD, sizeNormal);
        headingFont = new Font("Arial", BOLD, sizeHeading);
        textColor = new Color(255, 135, 17);
        foreground = new Color(255, 135, 17);
        background = new Color(38, 19, 0);
    }

    public static void setViolet(){
        sizeHeading = 40;
        sizeNormal = 12;
        sizeNote = 10;
        textFont = new Font("Arial", Font.PLAIN, sizeNormal);
        textFont_BOLD = new Font("Arial", BOLD, sizeNormal);
        headingFont = new Font("Arial", BOLD, sizeHeading);
        textColor = new Color(226, 109, 255);
        foreground = new Color(226, 109, 255);
        background = new Color(42, 0, 51);
    }

    public static void setBlue(){
        sizeHeading = 40;
        sizeNormal = 12;
        sizeNote = 10;
        textFont = new Font("Arial", Font.PLAIN, sizeNormal);
        textFont_BOLD = new Font("Arial", BOLD, sizeNormal);
        headingFont = new Font("Arial", BOLD, sizeHeading);
        textColor = new Color(0, 202, 253);
        foreground = new Color(0, 202, 253);
        background = new Color(0, 29, 38);
    }

    public static void setRandomThemeID(){
        double f = Math.random()/Math.nextDown(1.0);
        double rand = 0 * (1.0 - f) + MAX_NO_OF_THEMES * f;
        setThemeWithID((int) rand);
    }
}