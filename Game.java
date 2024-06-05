    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
import java.util.concurrent.Flow;
    import javax.swing.*;
import javax.swing.border.Border;


public class Game extends JFrame {

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private JPanel titleScreen;
    private JPanel gameScreen;
    private JPanel shopScreen;
    private JPanel gameOverScreen;
    private JPanel winScreen;

    private BackgroundPanel fightLabel;

    private JButton startButton;
    private JButton buyButton;
    private JButton AtkButton;
    private JButton DefButton;
    private JButton RelButton;
    private JButton restartButton; 

    private JLabel resLabel;
    private JLabel coinLabel;
    private JLabel statLabel = new JLabel("Ammo:      Health:      Enemy Health:  ");
    private JLabel banditImg;

    private ImageIcon hatIcon = new ImageIcon("/Users/raahulvenkatesan/Code Projects/Resources/hat.png");
    private Image origHatImg = hatIcon.getImage();
    private Image resizedHatImg = origHatImg.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
    private ImageIcon rezHatIcon = new ImageIcon(resizedHatImg);

    private ImageIcon banditIcon = new ImageIcon("/Users/raahulvenkatesan/Code Projects/Resources/bandit.png");
    private Image origBanditImg = banditIcon.getImage();
    private Image resizedBanditImg = origBanditImg.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
    private ImageIcon rezBanditIcon = new ImageIcon(resizedBanditImg);

    private ImageIcon baronIcon = new ImageIcon("/Users/raahulvenkatesan/Code Projects/Resources/baron.png");
    private Image origBaronImg = baronIcon.getImage();
    private Image resizedBaronImage = origBaronImg.getScaledInstance(250, 280, Image.SCALE_SMOOTH);
    private ImageIcon rezBaronIcon = new ImageIcon(resizedBaronImage);

    private ImageIcon skullIcon = new ImageIcon("/Users/raahulvenkatesan/Code Projects/Resources/endSkull.png");
    private Image origSkullImg = skullIcon.getImage();
    private Image resizedSkullImg = origSkullImg.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
    private ImageIcon rezSkullIcon = new ImageIcon(resizedSkullImg);

    private ImageIcon faceIcon = new ImageIcon("/Users/raahulvenkatesan/Code Projects/Resources/winFace.png");
    private Image origfaceImg = faceIcon.getImage();
    private Image resizedFaceImg = origfaceImg.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
    private ImageIcon rezFaceIcon = new ImageIcon(resizedFaceImg);

    public Player player = new Player();
    public Enemy computer;
    public Enemy baron = new Enemy(4, 0, 1);
    public Enemy bandit = new Enemy(2,0,1);
    public String enemyType = "bandit";



    public Game() {
        setTitle("West World");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        createTitleScreen();
        createGameScreen();
        createShopScreen();
        createGameOverScreen();
        createWinScreen();

        add(titleScreen, "Title");
        add(gameScreen, "Game");
        add(shopScreen, "Shop");
        add(gameOverScreen, "Game Over");
        add(winScreen, "Win");

        showTitleScreen();
    }

    private void createTitleScreen() {
        titleScreen = new JPanel();
        titleScreen.setLayout(new BorderLayout());
        titleScreen.setBorder(BorderFactory.createEmptyBorder(150,10,200,10));

        JLabel titleLabel = new JLabel("WEST WORLD");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel hatImg = new JLabel();
        hatImg.setIcon(rezHatIcon);
        hatImg.setHorizontalAlignment(SwingConstants.CENTER);

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGameScreen(0);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);

        titleScreen.add(hatImg, BorderLayout.NORTH);
        titleScreen.add(titleLabel, BorderLayout.CENTER);
        titleScreen.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void createGameScreen() {
        gameScreen = new JPanel();
        gameScreen.setLayout(new FlowLayout(0,0,0));

        AtkButton = new JButton("Attack");
        AtkButton.setOpaque(true);
        AtkButton.setFont(new Font("Serif",Font.BOLD, 15));
        AtkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doBattle(0);
            }
        });

        DefButton = new JButton("Defend");
        DefButton.setOpaque(true);
        DefButton.setFont(new Font("Serif",Font.BOLD, 15));
        DefButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doBattle(1);
            }
        });

        RelButton = new JButton("Reload");
        RelButton.setOpaque(true);
        RelButton.setFont(new Font("Serif",Font.BOLD, 15));
        RelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doBattle(2);
            }
        });

        banditImg = new JLabel();
        banditImg.setIcon(rezBanditIcon);
        banditImg.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel baronImg = new JLabel();
        baronImg.setIcon(rezBaronIcon);
        baronImg.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel titLabel = new JLabel("WEST WORLD");
        titLabel.setFont(new Font("Serif",Font.BOLD,30));
        titLabel.setBackground(Color.GRAY);
        titLabel.setOpaque(true);
        titLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        titLabel.setPreferredSize(new Dimension(800,60));

        ImageIcon woodenBarIcon = new ImageIcon("/Users/raahulvenkatesan/Code Projects/Resources/bar.png");
        fightLabel = new BackgroundPanel(woodenBarIcon.getImage());
        fightLabel.setPreferredSize(new Dimension(800, 320));
        fightLabel.setLayout(new BorderLayout());

        if (enemyType.equals("baron")) {
            fightLabel.remove(banditImg);
            fightLabel.add(baronImg, BorderLayout.CENTER);
        } else {
            fightLabel.add(banditImg, BorderLayout.CENTER);
        }
        

        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);

        JLabel buttonLabel = new JLabel("");
        buttonLabel.setLayout(new GridLayout(1,3));
        buttonLabel.setBackground(Color.GRAY);
        buttonLabel.setOpaque(true);
        buttonLabel.setPreferredSize(new Dimension(800,70));

        buttonLabel.add(AtkButton);
        buttonLabel.add(DefButton);
        buttonLabel.add(RelButton);

        statLabel.setText("Ammo:      Health:      Enemy Health:  ");
        statLabel.setFont(new Font("Serif",Font.BOLD,20));
        statLabel.setBackground(Color.GRAY);
        statLabel.setOpaque(true);
        statLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        statLabel.setPreferredSize(new Dimension(800,30));

        if (enemyType.equals("baron")){
            resLabel = new JLabel("A Greedy Baron has Appeared. Your Move!");
        } else {
            resLabel = new JLabel("A Pesky Bandit has Appeared. Your Move!");
        }
        resLabel.setFont(new Font("Serif",Font.BOLD,20));
        resLabel.setBackground(Color.GRAY);
        resLabel.setOpaque(true);
        resLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resLabel.setPreferredSize(new Dimension(800,100));

        gameScreen.add(titLabel);
        gameScreen.add(fightLabel);
        gameScreen.add(buttonLabel);
        gameScreen.add(statLabel);
        gameScreen.add(resLabel);

    }

    private void createShopScreen() {

    shopScreen = new JPanel();
    shopScreen.setLayout(new BorderLayout());
    shopScreen.setBorder(BorderFactory.createEmptyBorder(250,10,10,10));

    JLabel shopLabel = new JLabel("SHOP");
    shopLabel.setFont(new Font("Serif", Font.BOLD, 30));
    shopLabel.setHorizontalAlignment(SwingConstants.CENTER);

    coinLabel = new JLabel("Coins: " + player.getCoins());
    coinLabel.setFont(new Font("Serif", Font.BOLD, 30));

    JLabel actionLabel = new JLabel("");
    actionLabel.setFont(new Font("Serif", Font.BOLD, 30));

    buyButton = new JButton("Buy");
    buyButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (player.getCoins() >0) {
                player.subCoins(1);
                actionLabel.setText("Sucessfully Bought!");
            } else {
                actionLabel.setText("No Moolah");
            }
            coinLabel.setText("Coins: " + player.getCoins());
            player.saveCoins();
        }
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(buyButton);

    JPanel botPanel = new JPanel();
    botPanel.add(actionLabel);
    botPanel.add(coinLabel);

    shopScreen.add(shopLabel, BorderLayout.NORTH);
    shopScreen.add(buttonPanel, BorderLayout.CENTER);
    shopScreen.add(botPanel, BorderLayout.SOUTH);
    }

    private void createGameOverScreen() {
        gameOverScreen = new JPanel();
        gameOverScreen.setLayout(new BorderLayout());
        gameOverScreen.setBorder(BorderFactory.createEmptyBorder(100,10,150,10));
        gameOverScreen.setBackground(Color.BLACK);

        restartButton = new JButton("PLAY AGAIN?");
        restartButton.setFont(new Font("Serif", Font.BOLD,15));
        restartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showGameScreen(0);
            player.reset();
            }
        });
        restartButton.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel skullImg = new JLabel();
        skullImg.setIcon(rezSkullIcon);
        skullImg.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        buttonPanel.add(restartButton);
        buttonPanel.setBackground(Color.BLACK);
    
        gameOverScreen.add(skullImg, BorderLayout.NORTH);
        gameOverScreen.add(buttonPanel, BorderLayout.CENTER);

    }
    private void createWinScreen() {
        winScreen = new JPanel();
        winScreen.setLayout(new BorderLayout());
        winScreen.setBorder(BorderFactory.createEmptyBorder(100,10,150,10));
        winScreen.setBackground(new Color(100,100,200));

        restartButton = new JButton("Next Enemy");
        restartButton.setFont(new Font("Serif", Font.BOLD,15));
        restartButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            player.reset();
            enemyType = "baron";
            updateGameScreen();
            showGameScreen(1);
            }
        });
        restartButton.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel winImg = new JLabel();
        winImg.setIcon(rezFaceIcon);
        winImg.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(restartButton);
        buttonPanel.setBackground(new Color(100,100,200));
    
        winScreen.add(winImg, BorderLayout.NORTH);
        winScreen.add(buttonPanel, BorderLayout.CENTER);

    }

    private void updateGameScreen () {
        resLabel.setText("A Greedy Baron has Appeared. Your Move!");
        JLabel baronImg = new JLabel();
        baronImg.setIcon(rezBaronIcon);
        baronImg.setHorizontalAlignment(SwingConstants.CENTER);
        fightLabel.add(baronImg);
    }

    private void showTitleScreen() {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "Title");
    }

    private void showGameScreen(int n) {
        if (enemyType.equals("baron")) {
            computer = baron;
        } else {
            computer = bandit;
        }
        updateStatScreen();
        //updateGameScreen();
        if (n == 1) {
            fightLabel.remove(banditImg);
        }

        resetBattle();
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "Game");
    }

    private void showShopScreen() {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "Shop");
    }

    private void showGameOverScreen() {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "Game Over");
    }

    private void showWinScreen () {
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        cardLayout.show(getContentPane(), "Win");
    }

    private void updateStatScreen() {
        statLabel.setText("Ammo: " + player.getAmmo() + " Health: " + player.getHealth() + " Enemy Health: "+computer.getHealth());
        if (player.getHealth() == 1) {
            statLabel.setBackground(new Color(255, 100, 75));
        } else {
            statLabel.setBackground(Color.GRAY);
        }
    }

    private void resetBattle() {
        player.reset();
        baron.setHealth(4);
        bandit.setHealth(2);
    }

    private void doBattle(int playerChoice) { //0 is Attack, 1 is Defend, 2 is Reload
        int computerChoice = computer.moveDecision(); 

        System.out.println(player.getHealth());

        //Check Player Death or Computer Death
        if (computerChoice == 0 && (player.getHealth()-computer.getDamage()) <= 0) {
            if (playerChoice != 1) {
            resLabel.setText("You Have Died :(");
            player.setScore();
            showGameOverScreen();
            }
        } else if (playerChoice == 0 && (computer.getHealth()-player.getDamage()) <= 0) {
            resLabel.setText("You Have Won!\n+1 Coins");
            player.addCoins(1);
        } 

        if (playerChoice == 0) { //Player Attacks
            if (player.getAmmo() > 0) {
                player.setAmmo(player.getAmmo()-1);
                if (computerChoice == 0) {
                player.setHealth(player.getHealth()-computer.getDamage());
                
                computer.setHealth(computer.getHealth()-player.getDamage());
                computer.setAmmo(computer.getAmmo()-1);

                resLabel.setText("You and the Enemy attack each other.\nLose 1 Health\nLose 1 Ammo\n Do 1 Damage");
            } else if (computerChoice == 1) {
                resLabel.setText("Enemy Defends. Your Attack is unsuccesful.\nLose 1 Ammo");
            } else if (computerChoice == 2) {
                computer.reload();
                computer.setHealth(computer.getHealth()-player.getDamage());
                resLabel.setText("Enemy reloads. Your Attack is Successful.\nLose 1 Ammo\nDeal 1 Damage\n Enemy Reloads");
            }
            } else {
                resLabel.setText("No Ammo. Try Reloading.");
            }
        } else if (playerChoice == 1) { //Player Defends
            if (computerChoice == 0) {
                computer.setAmmo(computer.getAmmo()-1);
                resLabel.setText("Enemy Attacks. You Defend Successfully.");
            } else if (computerChoice == 1) {
                resLabel.setText("You and The Enemy Defend. Stalemate.");
            } else if (computerChoice == 2) {
                computer.reload();
                resLabel.setText("Enemy Reloads. You Defend Nothing.");
            }
        } else if (playerChoice == 2) { //Player Reloads
            player.reload();
            if (computerChoice == 0) {
                computer.setAmmo(computer.getAmmo()-1);
                player.setHealth(player.getHealth()-computer.getDamage());
                resLabel.setText("Enemy Attacks. You Reload Successfully.\nLose 1 Health.\nGain 1 Ammo");
            } else if (computerChoice == 1) {
                resLabel.setText("Enemy Defends. You Reload Successfully.\nGain 1 Ammo");
            } else if (computerChoice == 2) {
                computer.reload();
                resLabel.setText("Enemy Reloads. You Reload Successfully.\nGain 1 Ammo");
            }
        }

        if (player.getHealth() == 0) {
            resLabel.setText("You Died");
        } else if (computer.getHealth() == 0) {
            resLabel.setText("You Have Won!\n+1 Coins");
            showWinScreen();
        }
        updateStatScreen();
        
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game().setVisible(true);
            }
        });

    }
}



