import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

public class Game {

    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel, playerPanel;
    JLabel titleNameLabel, hpLabel, hpLabelNumber, weaponLabel, weaponLabelName;
    Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    Font normalFont = new Font("Times New Roman", Font.PLAIN, 22);
    Font smallFont = new Font("Times New Roman", Font.PLAIN, 14);
    JButton startButton, continueButton, choice1, choice2, choice3, choice4;
    JTextArea mainTextArea;
    int playerHP, goblinHP, ganondorfHP, silverRing, ganondorfStaff, townGateAccomplished,
            isGoblin, isGanondorf, goblinDefeated, ganondorfDefeated, coins, playerLvl,
            longSwordObtained, spearObtained, katanaObtained, wandObtained,
            longSwordPrice, spearPrice, katanaPrice, wandPrice, homelessComplete,
            foundMarthy, obtainedLocket, didTowerGuard, hasDrawerKey, kingSword, raiderDefeated,
            raiderHP, plus5DamageBought, appleBought, extraWeaponDamage, throneKey,
            questionAsked, talkedToPrisoner, talkedToDungeonGuard, prisonerFree, demonicDagger,
            didRed, didYellow, didBlue, superKey, basicSpellsRead, complexSpellsRead, elementalSpellsRead,
            throneRoomDone;
    String weapon, position;

    TitleScreenHandler tsHandler = new TitleScreenHandler();
    ChoiceHandler choiceHandler = new ChoiceHandler();

//    ImageIcon logo = new ImageIcon(".//res//Thumbnail.jpg");

    public static void main(String[] args) {

        new Game();
    }

    public Game(){

        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        window.setVisible(true);
        window.setResizable(false);
//        window.setIconImage(logo.getImage());
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("ADVENTURE");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 400, 200, 100);
        startButtonPanel.setBackground(Color.black);

        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.addActionListener(tsHandler);
        startButton.setFocusPainted(false);
        startButton.setActionCommand("start");

        continueButton = new JButton("CONTINUE");
        continueButton.setBackground(Color.black);
        continueButton.setForeground(Color.white);
        continueButton.setFont(normalFont);
        continueButton.addActionListener(tsHandler);
        continueButton.setFocusPainted(false);
        continueButton.setActionCommand("continue");

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        startButtonPanel.add(continueButton);

        con.add(titleNamePanel);
        con.add(startButtonPanel);
        window.setVisible(true);
    }

    public void createGameScreen(String startOrContinue){

        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(100, 100, 600, 250);
        mainTextPanel.setBackground(Color.black);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(100, 100, 600, 250);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);

        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(250, 350, 300, 150);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(4,1));
        con.add(choiceButtonPanel);

        choice1 = new JButton("Choice 1");
        choice1.setBackground(Color.black);
        choice1.setForeground(Color.white);
        choice1.setFont(normalFont);
        choice1.setFocusPainted(false);
        choice1.addActionListener(choiceHandler);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);
        choice2 = new JButton("Choice 2");
        choice2.setBackground(Color.black);
        choice2.setForeground(Color.white);
        choice2.setFont(normalFont);
        choice2.setFocusPainted(false);
        choice2.addActionListener(choiceHandler);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);
        choice3 = new JButton("Choice 3");
        choice3.setBackground(Color.black);
        choice3.setForeground(Color.white);
        choice3.setFont(normalFont);
        choice3.setFocusPainted(false);
        choice3.addActionListener(choiceHandler);
        choice3.setActionCommand("c3");
        choiceButtonPanel.add(choice3);
        choice4 = new JButton("Choice 4");
        choice4.setBackground(Color.black);
        choice4.setForeground(Color.white);
        choice4.setFont(normalFont);
        choice4.setFocusPainted(false);
        choice4.addActionListener(choiceHandler);
        choice4.setActionCommand("c4");
        choiceButtonPanel.add(choice4);

        playerPanel = new JPanel();
        playerPanel.setBounds(100, 15, 600, 50);
        playerPanel.setBackground(Color.black);
        playerPanel.setLayout(new GridLayout(1, 4));
        con.add(playerPanel);

        hpLabel = new JLabel("HP:");
        hpLabel.setFont(normalFont);
        hpLabel.setForeground(Color.white);
        playerPanel.add(hpLabel);
        hpLabelNumber = new JLabel();
        hpLabelNumber.setFont(normalFont);
        hpLabelNumber.setForeground(Color.white);
        playerPanel.add(hpLabelNumber);
        weaponLabel = new JLabel("Weapon:");
        weaponLabel.setFont(normalFont);
        weaponLabel.setForeground(Color.white);
        playerPanel.add(weaponLabel);
        weaponLabelName = new JLabel();
        weaponLabelName.setFont(smallFont);
        weaponLabelName.setForeground(Color.white);
        playerPanel.add(weaponLabelName);

        if(startOrContinue.equals("start")){
            playerSetup();
        }
        if(startOrContinue.equals("continue")){
            loadData();
        }
    }

    public void playerSetup(){
        playerHP=15;
        goblinHP=30;
        ganondorfHP=6000;
        goblinDefeated=0;
        ganondorfDefeated=0;
        silverRing=0;
        ganondorfStaff=0;
        weapon="Knife";
        coins=0;
        playerLvl=1;
        longSwordObtained=0;
        spearObtained=0;
        katanaObtained=0;
        wandObtained=0;
        homelessComplete=0;
        foundMarthy=0;
        obtainedLocket=0;
        didTowerGuard=0;
        hasDrawerKey=0;
        kingSword=0;
        raiderDefeated=0;
        raiderHP=100;
        plus5DamageBought=0;
        appleBought=0;
        extraWeaponDamage=0;
        throneKey=0;
        questionAsked=0;
        talkedToPrisoner=0;
        talkedToDungeonGuard=0;
        prisonerFree=0;
        demonicDagger=0;
        didRed=0;
        didYellow=0;
        didBlue=0;
        superKey=0;
        basicSpellsRead=0;
        complexSpellsRead=0;
        elementalSpellsRead=0;
        throneRoomDone=0;

        weaponLabelName.setText(weapon);
        hpLabelNumber.setText("" + playerHP);

        longSwordPrice = 100;
        spearPrice = 200;
        katanaPrice = 1000;
        wandPrice = 5000;


        townGateNorth();
    }

    public void loadData(){

        try{
            BufferedReader br = new BufferedReader(new FileReader("saveFile.txt"));
            playerHP = Integer.parseInt(br.readLine());
            goblinHP = Integer.parseInt(br.readLine());
            ganondorfHP = Integer.parseInt(br.readLine());
            goblinDefeated = Integer.parseInt(br.readLine());
            ganondorfDefeated = Integer.parseInt(br.readLine());
            silverRing = Integer.parseInt(br.readLine());
            ganondorfStaff = Integer.parseInt(br.readLine());
            weapon = br.readLine();
            coins = Integer.parseInt(br.readLine());
            playerLvl = Integer.parseInt(br.readLine());
            longSwordObtained = Integer.parseInt(br.readLine());
            spearObtained = Integer.parseInt(br.readLine());
            katanaObtained = Integer.parseInt(br.readLine());
            wandObtained = Integer.parseInt(br.readLine());
            homelessComplete = Integer.parseInt(br.readLine());
            foundMarthy = Integer.parseInt(br.readLine());
            obtainedLocket = Integer.parseInt(br.readLine());
            didTowerGuard = Integer.parseInt(br.readLine());
            hasDrawerKey = Integer.parseInt(br.readLine());
            kingSword = Integer.parseInt(br.readLine());
            raiderDefeated = Integer.parseInt(br.readLine());
            raiderHP = Integer.parseInt(br.readLine());
            plus5DamageBought = Integer.parseInt(br.readLine());
            appleBought = Integer.parseInt(br.readLine());
            extraWeaponDamage = Integer.parseInt(br.readLine());
            throneKey = Integer.parseInt(br.readLine());
            questionAsked = Integer.parseInt(br.readLine());
            talkedToPrisoner = Integer.parseInt(br.readLine());
            talkedToDungeonGuard = Integer.parseInt(br.readLine());
            prisonerFree = Integer.parseInt(br.readLine());
            demonicDagger = Integer.parseInt(br.readLine());
            didRed = Integer.parseInt(br.readLine());
            didYellow = Integer.parseInt(br.readLine());
            didBlue = Integer.parseInt(br.readLine());
            superKey = Integer.parseInt(br.readLine());
            basicSpellsRead = Integer.parseInt(br.readLine());
            complexSpellsRead = Integer.parseInt(br.readLine());
            elementalSpellsRead = Integer.parseInt(br.readLine());
            throneRoomDone = Integer.parseInt(br.readLine());

            br.close();
        }
        catch(Exception e){

        }

        weaponLabelName.setText(weapon);
        hpLabelNumber.setText("" + playerHP);

        townGateNorth();

    }

    public void townGateNorth(){
        position = "townGateNorth";
        mainTextArea.setText("You are at the northern gate of the town. " +
                "\nA guard is standing in front of you. " +
                "\n\nWhat do you do?");

        choice1.setText("Talk to the guard");
        choice2.setText("Attack the guard");
        choice3.setText("Leave");
        choice4.setText("Save Game");
    }

    public void talkGuard(){
        position = "talkGuard";
        mainTextArea.setText("Guard: Hello Stranger. I have never seen your face." +
                "\nI'm sorry, but we cannot let a stranger into our town.");
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void save(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("saveFile.txt"));

            bw.write("" + playerHP);
            bw.newLine();
            bw.write("" + goblinHP);
            bw.newLine();
            bw.write("" + ganondorfHP);
            bw.newLine();
            bw.write("" + goblinDefeated);
            bw.newLine();
            bw.write("" + ganondorfDefeated);
            bw.newLine();
            bw.write("" + silverRing);
            bw.newLine();
            bw.write("" + ganondorfStaff);
            bw.newLine();
            bw.write(weapon);
            bw.newLine();
            bw.write("" + coins);
            bw.newLine();
            bw.write("" + playerLvl);
            bw.newLine();
            bw.write("" + longSwordObtained);
            bw.newLine();
            bw.write("" + spearObtained);
            bw.newLine();
            bw.write("" + katanaObtained);
            bw.newLine();
            bw.write("" + wandObtained);
            bw.newLine();
            bw.write("" + homelessComplete);
            bw.newLine();
            bw.write("" + foundMarthy);
            bw.newLine();
            bw.write("" + obtainedLocket);
            bw.newLine();
            bw.write("" + didTowerGuard);
            bw.newLine();
            bw.write("" + hasDrawerKey);
            bw.newLine();
            bw.write("" + kingSword);
            bw.newLine();
            bw.write("" + raiderDefeated);
            bw.newLine();
            bw.write("" + raiderHP);
            bw.newLine();
            bw.write("" + plus5DamageBought);
            bw.newLine();
            bw.write("" + appleBought);
            bw.newLine();
            bw.write("" + extraWeaponDamage);
            bw.newLine();
            bw.write("" + throneKey);
            bw.newLine();
            bw.write("" + questionAsked);
            bw.newLine();
            bw.write("" + talkedToPrisoner);
            bw.newLine();
            bw.write("" + talkedToDungeonGuard);
            bw.newLine();
            bw.write("" + prisonerFree);
            bw.newLine();
            bw.write("" + demonicDagger);
            bw.newLine();
            bw.write("" + didRed);
            bw.newLine();
            bw.write("" + didYellow);
            bw.newLine();
            bw.write("" + didBlue);
            bw.newLine();
            bw.write("" + superKey);
            bw.newLine();
            bw.write("" + basicSpellsRead);
            bw.newLine();
            bw.write("" + complexSpellsRead);
            bw.newLine();
            bw.write("" + elementalSpellsRead);
            bw.newLine();
            bw.write("" + throneRoomDone);

            bw.close();

        }
        catch (Exception e){

        }
    }

    public void saveFromNorth(){
        position = "saveFromNorth";

        save();

        mainTextArea.setText("Saving game... " +
                "\n\n(Your progress has been saved)");
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

        hpLabelNumber.setText("" + playerHP);
        weaponLabelName.setText(weapon);
    }

    public void saveFromSouth(){
        position = "saveFromSouth";

        save();

        mainTextArea.setText("Saving game... " +
                "\n\n(Your progress has been saved)");
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

        hpLabelNumber.setText("" + playerHP);
        weaponLabelName.setText(weapon);
    }

    public void attackGuard(){
        position = "attackGuard";
        int randomDamage = (int)(Math.random()*4);
        mainTextArea.setText("Guard: Hey, don't be stupid! " +
                "\nThe guard fought back and hit you hard. " +
                "\n\n(You receive " + randomDamage + " damage)");
        playerHP -= randomDamage;
        if(playerHP <=0){
            playerHP=0;
        }
        hpLabelNumber.setText("" + playerHP);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void attackGuard2(){
        position = "attackGuard2";
        int randomDamage = (int)(Math.random()*4);
        mainTextArea.setText("Guard: Hey, don't be stupid! " +
                "\nThe guard fought back and hit you hard. " +
                "\n\n(You receive " + randomDamage + " damage)");
        playerHP -= randomDamage;
        if(playerHP <=0){
            playerHP=0;
        }
        hpLabelNumber.setText("" + playerHP);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void attackGuard3(){
        position = "attackGuard3";
        int randomDamage = (int)(Math.random()*4);
        mainTextArea.setText("Guard: Hey, don't be stupid! " +
                "\nThe guard fought back and hit you hard. " +
                "\n\n(You receive " + randomDamage + " damage)");
        playerHP -= randomDamage;
        if(playerHP <=0){
            playerHP=0;
        }
        hpLabelNumber.setText("" + playerHP);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void crossRoad(){
        position = "crossRoad";
        mainTextArea.setText("You are at a crossroad. " +
                "\nIf you go south, you will go back to to the northern town gate.");
        choice1.setText("Go North");
        choice2.setText("Go East");
        choice3.setText("Go South");
        choice4.setText("Go West");
    }

    public void north(){
        position = "north";
        int randomHeal = (int)(Math.random()*3);
        if(randomHeal==0){
            randomHeal = 1;
        }
        if(playerHP>=25){
            mainTextArea.setText("There is a river. " +
                    "\nYou drink the water and rest and the riverside. " +
                    "\n\n(Your HP is already at max)");
        } else {
            mainTextArea.setText("There is a river. " +
                    "\nYou drink the water and rest and the riverside. " +
                    "\n\n(Your HP is recovered by "+ randomHeal +")");
            playerHP += randomHeal;
            if(playerHP>25){
                playerHP=25;
            }
            hpLabelNumber.setText("" + playerHP);
        }
        choice1.setText("Go South");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void east(){
        position = "east";
        if(weapon.equals("Knife")){
        mainTextArea.setText("You walked into a forest and found a Sword! " +
                "\n\n(You obtained a Sword)");
        weapon = "Sword";
        weaponLabelName.setText(weapon);
        } else {
            mainTextArea.setText("You walk through the forest and find nothing");
        }
        choice1.setText("Go West");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");

    }

    public void west(){
        position = "west";
        if(goblinDefeated == 1 && ganondorfDefeated == 1){
            position = "noMonster";
            mainTextArea.setText("You walk through the woods and find nothing.");
            choice1.setText("Go East");
            choice2.setText("");
        } else if(goblinDefeated == 1){
            {
                isGanondorf = 1;
                mainTextArea.setText("You encounter the wizard Ganondorf");
                choice1.setText("Fight");
                choice2.setText("Run");
            }
        } else{
            isGoblin = 1;
            mainTextArea.setText("You encounter a goblin!");
            choice1.setText("Fight");
            choice2.setText("Run");
        }
        choice3.setText("");
        choice4.setText("");
    }

    public void fight(){
        position = "fight";
        if(isGoblin == 1){
            mainTextArea.setText("Enemy HP: " + goblinHP +
                    "\n\nWhat do you want to do?");
        } else{
            mainTextArea.setText("Enemy HP: " + ganondorfHP +
                    "\n\nWhat do you want to do?");
        }
        choice1.setText("Attack");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");
    }

    public void playerAttack(){
        position = "playerAttack";

        int playerDamage = 0;
        if(weapon.equals("Knife")){
            playerDamage = new Random().nextInt(5);
        }
        if(weapon.equals("Sword")){
            playerDamage = new Random().nextInt(10);
        }
        if(weapon.equals("Long Sword")){
            playerDamage = new Random().nextInt(15);
        }
        if(weapon.equals("Spear")){
            playerDamage = new Random().nextInt(20);
        }
        if(weapon.equals("Katana")){
            playerDamage = new Random().nextInt(50);
        }
        if(weapon.equals("Wand")){
            playerDamage = new Random().nextInt(150);
        }
        if(weapon.equals("Demonic Dagger")){
            playerDamage = new Random().nextInt(2000);
        }
        if(weapon.equals("Ganondorf's Staff")){
            playerDamage = new Random().nextInt(6000);
        }

        playerDamage += extraWeaponDamage;

        if(playerDamage!=0){
            if(isGoblin == 1){
                mainTextArea.setText("You attacked the goblin and gave " + playerDamage + " damage!");
            } else {
                mainTextArea.setText("You attacked Ganondorf and gave " + playerDamage + " damage!");
            }
        } else{
            mainTextArea.setText("You missed");
        }
        if(isGoblin==1){
            goblinHP -= playerDamage;
        } else{
            ganondorfHP -= playerDamage;
        }
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void monsterAttack(){
        position = "monsterAttack";

        int monsterAttackDamage;

        if(isGoblin == 1) {
            monsterAttackDamage = new Random().nextInt(3);
        } else{
            monsterAttackDamage = new Random().nextInt(6000);
        }
        if(isGoblin == 1) {
            mainTextArea.setText("The goblin attacked you and gave " + monsterAttackDamage + " damage!");
        } else{
            mainTextArea.setText("Ganondorf attacked you and gave " + monsterAttackDamage + " damage!");
        }
        playerHP -= monsterAttackDamage;
        if(playerHP <=0){
            playerHP = 0;
        }
        hpLabelNumber.setText("" + playerHP);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void winGoblin(){
        position = "winGoblin";

        mainTextArea.setText("You defeated the goblin! " +
                "\nThe goblin dropped a ring " +
                "\n\n(You obtained a Silver Ring and 100 coins)");
        silverRing = 1;
        goblinDefeated = 1;
        isGoblin = 0;
        coins += 50;
        choice1.setText("Go East");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void winGanondorf(){
        position = "winGanondorf";

        mainTextArea.setText("You defeated Ganondorf and took his staff! " +
                "\n\n(You obtained Ganondorf's Staff and 5000 coins)");
        ganondorfDefeated = 1;
        ganondorfStaff = 1;
        coins += 5000;
        weapon = "Ganondorf's Staff";
        weaponLabelName.setText(weapon);
        choice1.setText("Go East");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void lose(){
        position = "lose";

        mainTextArea.setText("You died! " +
                "\n\n<GAME OVER>");
        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    public void killedGoblin(){
        position = "killedGoblin";
        if(townGateAccomplished==0){
        mainTextArea.setText("Guard: Oh you killed that goblin!? " +
                "\nThank you so much! " +
                "\nWelcome to our town!");
        } else{
            mainTextArea.setText("Guard: Ah, you again! I see you have the silver ring! " +
                    "\nThat goblin has been haunting us for ages, please come in!");
        }
        townGateAccomplished=1;
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void townSquare(){
        position = "townSquare";

        mainTextArea.setText("You are at the center of the town, what do you want to do?");

        choice1.setText("Enter the shop");
        choice2.setText("Look for homeless people");
        choice3.setText("Go to the watch tower");
        choice4.setText("Leave");
    }

    public void shop(){
        position = "shop";

        longSwordPrice = 100;
        spearPrice = 200;
        katanaPrice = 1000;
        wandPrice = 5000;

        mainTextArea.setText("Shop Owner: Welcome to my humble shop! " +
                "\nWould you like to buy something?");

        choice1.setText("Weapons");
        choice2.setText("Consumables");
        choice3.setText("Leave");
        choice4.setText("");
    }

    public void weaponShop(){
        position = "weaponShop";

        longSwordPrice = 100;
        spearPrice = 200;
        katanaPrice = 1000;
        wandPrice = 5000;

        mainTextArea.setText("Shop Owner: Here are my weapons for sale " +
                "\n\n(You have " + coins + " coins)");

        choice1.setText("You have already bought this item");
        choice2.setText("You have already bought this item");
        choice3.setText("You have already bought this item");
        choice4.setText("You have already bought this item");

        if(longSwordObtained==0){
            choice1.setText("Long Sword - 15 Damage");
        }
        if(spearObtained==0){
            choice2.setText("Spear - 20 Damage");
        }
        if(katanaObtained==0){
            choice3.setText("Katana - 50 Damage");
        }
        if(wandObtained==0){
            choice4.setText("Wand - 150 Damage");
        }
    }

    public void longSword(){
        position = "longSword";

        if(longSwordObtained!=0){
            mainTextArea.setText("You already bought this item");
        } else if(spearObtained!=0 || katanaObtained !=0 || wandObtained!=0 || ganondorfStaff!=0 || demonicDagger!=0){
            mainTextArea.setText("You already have an item higher than this");
        }else if(coins<longSwordPrice){
            int difference = longSwordPrice-coins;
            mainTextArea.setText("You don't have enough coins for this purchase " +
                    "\nYou need " + difference + " more coins");
        } else{
            mainTextArea.setText("You bought a Long Sword! Congratulations! " +
                    "\n\n(Long Sword Obtained)");
            longSwordObtained=1;
            weapon = "Long Sword";
            weaponLabelName.setText(weapon);
        }

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void spear(){
        position = "spear";

        if(spearObtained!=0){
            mainTextArea.setText("You already bought this item");
        } else if(katanaObtained !=0 || wandObtained!=0 || ganondorfStaff!=0 || demonicDagger!=0){
            mainTextArea.setText("You already have an item higher than this");
        }else if(coins<spearPrice){
            int difference = spearPrice-coins;
            mainTextArea.setText("You don't have enough coins for this purchase. " +
                    "\nYou need " + difference + " more coins");
        } else{
            mainTextArea.setText("You bought a Spear! Congratulations! " +
                    "\n\n(Spear Obtained)");
            spearObtained=1;
            weapon = "Spear";
            weaponLabelName.setText(weapon);
        }

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void katana(){
        position = "katana";

        if(katanaObtained!=0){
            mainTextArea.setText("You already bought this item");
        } else if(wandObtained!=0 || ganondorfStaff!=0 || demonicDagger!=0){
            mainTextArea.setText("You already have an item higher than this");
        }else if(coins<katanaPrice){
            int difference = katanaPrice-coins;
            mainTextArea.setText("You don't have enough coins for this purchase. " +
                    "\nYou need " + difference + " more coins");
        } else{
            mainTextArea.setText("You bought a Katana! Congratulations! " +
                    "\n\n(Katana Obtained)");
            katanaObtained=1;
            weapon = "Katana";
            weaponLabelName.setText(weapon);
        }

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void wand(){
        position = "wand";

        if(wandObtained!=0){
            mainTextArea.setText("You already bought this item");
        } else if(ganondorfStaff!=0 || demonicDagger!=0){
            mainTextArea.setText("You already have an item higher than this");
        }else if(coins<wandPrice){
            int difference = wandPrice-coins;
            mainTextArea.setText("You don't have enough coins for this purchase. " +
                    "\nYou need " + difference + " more coins");
        } else{
            mainTextArea.setText("You bought a Wand! Congratulations! " +
                    "\n\n(Wand Obtained)");
            wandObtained=1;
            weapon = "Wand";
            weaponLabelName.setText(weapon);
        }

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void tooLowLevelForConsumableShop(){
        position = "tooLowLevelForConsumableShop";

        mainTextArea.setText("Shop Owner: " +
                "\nSorry sir, but you are not allowed in that part of the shop. " +
                "\n\n(Requirements Level 2)");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void consumableShop(){
            position = "consumableShop";

        mainTextArea.setText("Shop Owner: Welcome to my consumable shop! " +
                "\nPlease, buy something!");

        choice1.setText("Instant 10000 HP");
        choice2.setText("Weapon does +5 Damage");
        choice3.setText("An apple");
        choice4.setText("Leave");
    }

    public void searchForHomeless(){
        position = "searchForHomeless";


        if(foundMarthy!=1 && appleBought==0){
            mainTextArea.setText("Marthy: Hi sonny! My name's Marthy! " +
                    "\nI've been living here in the streets for 10 years! " +
                    "\n\nI'm very hungry, could I please have an apple son?");
            foundMarthy=1;
        } else if(foundMarthy==1 && appleBought==0){
            mainTextArea.setText("Marthy: Hi sonny! Do you have the apple? No?? " +
                    "\nPlease, I'm really hungry!");
        } else if (homelessComplete==1){
            mainTextArea.setText("The streets are bare");
        } else{
            mainTextArea.setText("Marthy: Is that an apple I see! Thank you so much sonny! " +
                    "\nHere, have this locket. It is very special to a friend of mine! " +
                    "\n\n(You obtained a locket)");
            obtainedLocket=1;
            homelessComplete=1;
        }

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void towerGuard(){
        position = "towerGuard";

        if(obtainedLocket!=0){
            if(didTowerGuard==0){
                mainTextArea.setText("Tower Guard: Sir, wait! Is that my grandmother's locket? " +
                    "\nWhere did you find it! I've been searching for that for years! " +
                    "\n\nPlease, you may enter");
                didTowerGuard=1;
            } else{
                mainTextArea.setText("Tower Guard: Ah, you again! Go right ahead!");
            }

        } else {
            mainTextArea.setText("Tower Guard: Sorry Sir, no one is allowed past this point");
        }
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void watchTower(){
        position = "watchTower";

        mainTextArea.setText("You are at the center of the watch tower. " +
                "\n\nWhat do you want to do?");

        choice1.setText("Search the drawers");
        choice2.setText("Go into the closet");
        choice3.setText("Go into the weapon room");
        choice4.setText("Leave");
    }

    public void leaveTown(){
        position = "leaveTown";

        mainTextArea.setText("What do you want to do?");

        choice1.setText("Exit North");
        choice2.setText("Exit South");
        choice3.setText("Stay in town");
        choice4.setText("");
    }

    public void townGateSouth(){
        position = "townGateSouth";

        mainTextArea.setText("You are at the southern gate of the town. " +
                "\nA guard is standing in front of you. " +
                "\n\nWhat do you do?");

        choice1.setText("Talk to the guard");
        choice2.setText("Attack the guard");
        choice3.setText("Leave");
        choice4.setText("Save Game");
    }

    public void crossRoad2(){
        position = "crossRoad2";

        mainTextArea.setText("You are at a crossroad. " +
                "\nIf you go north, you will go back to the southern town gate");
        choice1.setText("Go North");
        choice2.setText("Go East");
        choice3.setText("Go South");
        choice4.setText("Go West");
    }

    public void raider(){
        position = "raider";

        mainTextArea.setText("You encounter a Lvl. 2 Raider");


        choice1.setText("Fight");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");
    }

    public void fight2(){
        position = "fight2";

        mainTextArea.setText("Enemy HP: " + raiderHP +
                    "\n\nWhat do you want to do?");

        choice1.setText("Attack");
        choice2.setText("Run");
        choice3.setText("");
        choice4.setText("");
    }

    public void playerAttack2(){
        position = "playerAttack2";

        int playerDamage = 0;
        if(weapon.equals("Knife")){
            playerDamage = new Random().nextInt(5);
        }
        if(weapon.equals("Sword")){
            playerDamage = new Random().nextInt(10);
        }
        if(weapon.equals("Long Sword")){
            playerDamage = new Random().nextInt(15);
        }
        if(weapon.equals("Spear")){
            playerDamage = new Random().nextInt(20);
        }
        if(weapon.equals("Katana")){
            playerDamage = new Random().nextInt(50);
        }
        if(weapon.equals("Wand")){
            playerDamage = new Random().nextInt(150);
        }
        if(weapon.equals("Demonic Dagger")){
            playerDamage = new Random().nextInt(2000);
        }
        if(weapon.equals("Ganondorf's Staff")){
            playerDamage = new Random().nextInt(6000);
        }

        playerDamage += extraWeaponDamage;

        if(playerDamage!=0){
                mainTextArea.setText("You attacked the raider and gave " + playerDamage + " damage!");
        } else{
            mainTextArea.setText("You missed");
        }

        raiderHP -= playerDamage;
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void monsterAttack2(){
        position = "monsterAttack2";

        int monsterAttackDamage;

        monsterAttackDamage = new Random().nextInt(10);

        mainTextArea.setText("The raider attacked you and gave " + monsterAttackDamage + " damage!");

        playerHP -= monsterAttackDamage;
        if(playerHP <=0){
            playerHP = 0;
        }
        hpLabelNumber.setText("" + playerHP);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void winRaider(){
            position = "winRaider";

            mainTextArea.setText("You defeated the raider! " +
                    "\n\n(You leveled up and got 200 coins)");
            raiderDefeated = 1;
            playerLvl=2;
            coins += 100;
            choice1.setText("Go North");
            choice2.setText("");
            choice3.setText("");
            choice4.setText("");
    }

    public void east2(){
        position = "east2";

            mainTextArea.setText("Ahead of you is a cabin that looks mysterious. " +
                "\nThe door is locked and seems to be only able to open with some sort of staff");

        choice1.setText("Go West");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void west2(){
        position = "west2";
        int randomHeal = (int)(Math.random()*15);
        if(randomHeal<5){
            randomHeal = 5;
        }
        if(playerHP>=100){
            mainTextArea.setText("There is a river. " +
                    "\nYou drink the water and rest and the riverside. " +
                    "\n\n(Your HP is already at max)");
        } else {
            mainTextArea.setText("There is a river. " +
                    "\nYou drink the water and rest and the riverside. " +
                    "\n\n(Your HP is recovered by "+ randomHeal +")");
            playerHP += randomHeal;
            if(playerHP>100){
                playerHP=100;
            }
            hpLabelNumber.setText("" + playerHP);
        }
        choice1.setText("Go East");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void enterGanondorfsCabin(){
        position = "enterGanondorfsCabin";

        mainTextArea.setText("The door creaks open and you enter Ganondorf's Cabin");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void searchDrawers(){
        position = "drawers";

        if(hasDrawerKey!=0 && kingSword==0) {
            mainTextArea.setText("The drawer rattles open and inside, you find a sword " +
                    "\n\n(You obtained the king's sword)");
            kingSword=1;
        } else if(hasDrawerKey!=0){
            mainTextArea.setText("The drawer is empty");
        } else {
            mainTextArea.setText("The drawer seems to be locked, but there is a keyhole. " +
                    "\nI wonder if there is a key nearby");
        }

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void enterCloset(){
        position = "closet";

        mainTextArea.setText("There's nothing in here but some old clothes");

        choice1.setText("Search the clothes");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void enterWeaponRoom(){
        position = "weaponRoom";

        mainTextArea.setText("All the weapons are locked to the wall with no visible way to unlock " +
                "\nSeems like you won't be stealing these weapons");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void searchClothes(){
        position = "searchClothes";

        if(hasDrawerKey==0) {
            mainTextArea.setText("You stick your hand in one of the clothes pockets and find a key! " +
                    "\n(You obtained a mysterious key)");
            hasDrawerKey=1;
        } else{
            mainTextArea.setText("There's nothing in the clothes");
        }
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void instantHP(){
        position = "instantHP";

        int difference = 5000-coins;

            if(coins<5000){
                mainTextArea.setText("You don't have enough coins for this item " +
                        "\n(You need " + difference + " more coins)");
            } else {
                mainTextArea.setText("Congratulations, you bought 10000HP");
                coins -= 5000;
                playerHP += 10000;
            }
            hpLabelNumber.setText("" + playerHP);

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void plus5Damage(){
        position = "plus5Damage";

        int difference = 25-coins;

        if(plus5DamageBought==0){
            if(coins>=25){
                mainTextArea.setText("Congratulation, you just bought +5 damage");
                extraWeaponDamage+=5;
                coins -=25;
                plus5DamageBought=1;
            } else{
                mainTextArea.setText("You don't have enough coins for this purchase " +
                        "\nYou need " + difference + " more coins");
            }
        } else {
            mainTextArea.setText("You already bought this item");
        }

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void apple(){
        position = "apple";

        if(appleBought==0){
            mainTextArea.setText("Oh yes, the apple is on the house. " +
                "\nHere you go!");
        } else {
            mainTextArea.setText("I already gave you an apple");
        }
        appleBought=1;
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void townGate2(){
        position = "townGate2";

        mainTextArea.setText("You find a new town! In front of the town is a guard. " +
                "\nWhat do you want to do?");

        choice1.setText("Talk to the guard");
        choice2.setText("Attack the guard");
        choice3.setText("Go North");
        choice4.setText("");
    }

    public void talkGuard2(){
        position = "talkGuard2";

        mainTextArea.setText("Guard: Excuse me sir! What do you think you're doing!? " +
                "\nYou can not enter this town without a proper pass! " +
                "\nLeave and never come back!");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void passGuard2(){
        position = "passGuard2";

        mainTextArea.setText("Guard: Ah! I see you have the king's sword! " +
                "\nGo, give the sword to him. " +
                "\nI heard he has a large reward for its return");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void townSquare2(){
        position = "townSquare2";

        mainTextArea.setText("You are in the center of a town. " +
                "\nWhat do you want to do?");

        choice1.setText("Enter the throne");
        choice2.setText("Go into the barracks");
        choice3.setText("Enter the dungeons");
        choice4.setText("Leave");
    }

    public void leaveTown2(){
        position = "leaveTown2";

        mainTextArea.setText("What do you want to do?");

        choice1.setText("Go north");
        choice2.setText("Stay in town");
        choice3.setText("");
        choice4.setText("");
    }

    public void cells(){
        position = "cells";

        mainTextArea.setText("There is someone in one of the dungeon cells");

        choice1.setText("Talk to the guard");
        choice2.setText("Talk to him");
        choice3.setText("Leave");
        choice4.setText("");
    }

    public void talkToDungeonGuard(){
        position = "talkToDungeonGuard";

        mainTextArea.setText("Dungeon Guard: Who are you? Why are you here?");

        choice1.setText("I'm setting him free");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void thanks(){
        position = "thanks";

        mainTextArea.setText("Prisoner: Thanks you so much for setting me free! " +
                "\nHere, have this. It's the weapon I told you about " +
                "\n\n(You obtained the Demonic Dagger)");

        demonicDagger=1;
        weapon = "Demonic Dagger";
        weaponLabelName.setText(weapon);
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void setFree(){
        position = "setFree";

        mainTextArea.setText("Dungeon Guard: That would be 10000 coins");

        choice1.setText("Pay 10000 coins");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void notEnoughCoins(){
        position = "notEnoughCoins";

        mainTextArea.setText("You don't have enough coins for this purchase");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void pay10000(){
        position = "pay10000";

        coins-=10000;

        mainTextArea.setText("You successfully paid 10000 coins and set the prisoner free " +
                "\n\n(You have "+ coins +" coins left)");

        prisonerFree=1;
        talkedToDungeonGuard=1;
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void dungeonGuardLeave(){
        position = "dungeonGuardLeave";

        mainTextArea.setText("Dungeon Guard: You again? Just leave already");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void talkToPrisoner1(){
        position = "talkToPrisoner1";

        mainTextArea.setText("Prisoner: Sir please let me out! I didn't do anything wrong!");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void talkToPrisoner2(){
        position = "talkToPrisoner2";

        mainTextArea.setText("Why did you get locked up then?");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void talkToPrisoner3(){
        position = "talkToPrisoner3";

        mainTextArea.setText("Prisoner: I found this weapon on my doorstep that looks violent. " +
                "\nBut then guards showed up and took me here to the dungeons. " +
                "\nIf you let me free, you won't regret it!");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void talkToPrisoner4(){
        position = "talkToPrisoner4";

        mainTextArea.setText("How can I set you free?");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void talkToPrisoner5(){
        position = "talkToPrisoner5";

        mainTextArea.setText("Prisoner: You'll have to pay the dungeon guard 10000 coins");
        talkedToPrisoner=1;
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void prisonerNoTalk(){
        position = "prisonerNoTalk";

        mainTextArea.setText("Prisoner: Please, just set me free!");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void throneLocked(){
        position = "throneLocked";

        mainTextArea.setText("You walk up the the large throne gates, but they seem to be locked " +
                "\nYou'll have to find the key somewhere");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void throne(){
        position = "throne";

        mainTextArea.setText("King: WHO DARES ENTER MY THRONE " +
                "\nWITHOUT MY COMMAND!! " +
                "\nWait, is that my sword!? Thank you so very much! " +
                "\nHere is your reward. " +
                "\n\n(You obtained 100000 coins and leveled up!)");
        throneRoomDone=1;
        coins += 100000;
        playerLvl = 3;

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void throneRoomDone(){
        position = "throneRoomDone";

        mainTextArea.setText("The king is not here anymore");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void tooLowLevelForDungeon(){
        position = "tooLowLevelForDungeon";

        mainTextArea.setText("You may not enter this area " +
                "\n\n(Requirements Level 3)");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void dungeonEntrance(){
        position = "dungeonEntrance";

        mainTextArea.setText("You open the door to the dungeon and a chill drops down your spine");

        choice1.setText("To the cells");
        choice2.setText("Leave");
        choice3.setText("");
        choice4.setText("");
    }

    public void barracks(){
        position = "barracks";

        mainTextArea.setText("You are greeted with many solders, practicing their techniques");

        choice1.setText("Go into the locker room");
        choice2.setText("Ask someone a question");
        choice3.setText("Leave");
        choice4.setText("");
    }

    public void askQuestion(){
        position = "askQuestion";

        mainTextArea.setText("Solder: What do you want?! Can't you see I'm training here!");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void askingQuestion(){
        position = "askingQuestion";

        mainTextArea.setText("How do I enter the throne room?");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void questionAnswer(){
        position = "questionAnswer";

        mainTextArea.setText("Solder: Go into the locker room. " +
                "\nThere should be a key somewhere around there");

        questionAsked=1;
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void noQuestions(){
        position = "noQuestions";

        mainTextArea.setText("Solder: I already answer a question, now leave!");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void lockerRoom(){
        position = "lockerRoom";

        mainTextArea.setText("You find a key on someone's shirt " +
                "\n\n(You obtained the Throne Key)");

        throneKey=1;
        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void nothingInLockerRoom(){
        position = "nothingInLockerRoom";

        mainTextArea.setText("There's nothing here");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void noPrisoner(){
        position = "noPrisoner";

        mainTextArea.setText("There is no one here, you already set him free");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void ganondorfsCabin(){
        position = "ganondorfsCabin";

        mainTextArea.setText("There are many objects around you. " +
                "\n\nWhat do you want to do?");

        choice1.setText("Look at the potions");
        choice2.setText("Search the bookshelf");
        choice3.setText("Eat some of the food");
        choice4.setText("Leave");
    }

    public void lookAtPotions(){
        position = "lookAtPotions";

        mainTextArea.setText("There are many potions on the table. " +
                "\nWhich one do you want to drink?");

        choice1.setText("Red");
        choice2.setText("Yellow");
        choice3.setText("Blue");
        choice4.setText("None");
    }

    public void lookAtBookshelf(){
        position = "lookAtBookshelf";

        mainTextArea.setText("There are three books on the shelf, pick one");

        choice1.setText("The book of basic spells");
        choice2.setText("The book of complex spells");
        choice3.setText("The book of elemental spells");
        choice4.setText("Leave");
    }

    public void basicSpells(){
        position = "basicSpells";

        mainTextArea.setText("You read the book and gain +5 attack damage");

        extraWeaponDamage+=5;
        basicSpellsRead=1;

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void complexSpells(){
        position = "complexSpells";

        mainTextArea.setText("You read the book and gain +10HP and +10 attack damage");

        extraWeaponDamage+=10;
        playerHP+=10;
        complexSpellsRead=1;

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void elementalSpells(){
        position = "elementalSpells";

        mainTextArea.setText("The elemental spell book tells you that to win, " +
                "\nyou need to drink the blue potion");

        elementalSpellsRead=1;
        superKey+=1;

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void alreadyRead(){
        position = "alreadyRead";

        mainTextArea.setText("You already read this book");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void eatFood(){
        position = "eatFood";

        mainTextArea.setText("The food minuses your health but increases your damage " +
                "\n\n(You lost 10HP, and gained +10 Damage)");

        playerHP-=10;
        extraWeaponDamage+=10;

        hpLabelNumber.setText("" + playerHP);

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void red(){
        position = "red";

        mainTextArea.setText("You instantly gained 500HP! " +
                "\n\n(You obtained 500HP)");
        playerHP+=500;
        didRed=1;

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void yellow(){
        position = "yellow";

        mainTextArea.setText("Oh no, it seems as if your muscles are hurting! " +
                "\n\n(You lost 10HP and -10 damage)");
        playerHP-=10;
        extraWeaponDamage-=10;
        didYellow=1;

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void blue(){
        position = "blue";

        mainTextArea.setText("You drank the potion. " +
                "\nYou feel as if something incredible happened. " +
                "\nMaybe if you check one of the spell books, you might understand!");

        superKey+=1;
        didBlue=1;
        if(playerHP<=200){
            playerHP=200;
        }

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void noRed(){
        position = "noRed";

        mainTextArea.setText("You already drank red");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void noYellow(){
        position = "noYellow";

        mainTextArea.setText("You already drank yellow");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void noBlue(){
        position = "noBlue";

        mainTextArea.setText("You already drank blue");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void cabinExits(){
        position = "cabinExits";

        mainTextArea.setText("There are two exits, front and back " +
                "\nWhich one do you want to use?");

        choice1.setText("Front");
        choice2.setText("Back");
        choice3.setText("");
        choice4.setText("");
    }

    public void endingLocked(){
        position = "endingLocked";

        mainTextArea.setText("The back door is locked, there must be a way in this cabin to open it");

        choice1.setText(">");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
    }

    public void gameEnding(){
        position = "gameEnding";

        int weaponDamage=6000+extraWeaponDamage;

        int finalScore = (playerHP*10) + (weaponDamage*10);

        mainTextArea.setText("CONGRATULATIONS! YOU BEAT THE GAME! " +
                "\n\nFINAL SCORE: " + finalScore + " POINTS!");

        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        choice1.setVisible(false);
        choice2.setVisible(false);
        choice3.setVisible(false);
        choice4.setVisible(false);
    }

    public class TitleScreenHandler implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            String yourChoice = e.getActionCommand();

            switch (yourChoice){
                case "start":
                    createGameScreen("start");
                    break;
                case "continue":
                    createGameScreen("continue");
                    break;
            }
        }
    }

    public class ChoiceHandler implements ActionListener{

        public void actionPerformed(ActionEvent e){

            String yourChoice = e.getActionCommand();

            switch (position) {
                case "townGateNorth":
                    switch (yourChoice){
                        case "c1": if(silverRing==1){
                            killedGoblin();
                        } else {
                            talkGuard();
                        } break;
                        case "c2": attackGuard(); break;
                        case "c3": crossRoad(); break;
                        case "c4": saveFromNorth(); break;
                        default: break;
                    } break;
                case "talkGuard":
                case "attackGuard":
                    switch (yourChoice){
                        case "c1": if(playerHP<=0){
                            lose();
                        } else {
                            townGateNorth();
                        }break;
                        default: break;
                    } break;
                case "attackGuard2":
                case "saveFromSouth":
                    switch (yourChoice){
                        case "c1": townGateSouth(); break;
                        default: break;
                    } break;
                case "attackGuard3":
                case "talkGuard2":
                    switch (yourChoice){
                        case "c1": townGate2(); break;
                        default: break;
                    } break;
                case "crossRoad":
                    switch (yourChoice){
                        case "c1": north(); break;
                        case "c2": east(); break;
                        case "c3": townGateNorth(); break;
                        case "c4": west(); break;
                    } break;
                case "north":
                case "east":
                case "winGoblin":
                    switch (yourChoice){
                        case "c1": crossRoad(); break;
                        default: break;
                    }break;
                case "west":
                    switch (yourChoice){
                        case "c1": fight(); break;
                        case "c2": crossRoad(); break;
                        default: break;
                    }break;
                case "fight":
                    switch (yourChoice){
                        case "c1": playerAttack(); break;
                        case "c2": crossRoad(); break;
                        default: break;
                    }break;
                case "playerAttack":
                    switch (yourChoice){
                        case "c1":
                            if(isGoblin==1 && goblinHP<=0){
                                winGoblin();
                            } else if(isGanondorf==1 && ganondorfHP<=0){
                                winGanondorf();
                            } else {
                                monsterAttack();
                            }break;
                        default: break;
                    } break;
                case "monsterAttack":
                    switch (yourChoice){
                        case "c1":
                            if(playerHP<1){
                                lose();
                            } else{
                                fight();
                            } break;
                        default: break;
                    }break;
                case "noMonster":
                case "winGanondorf":
                    switch (yourChoice){
                        case "c1": crossRoad();
                        default: break;
                    } break;
                case "killedGoblin":
                case "searchForHomeless":
                    switch (yourChoice){
                        case "c1": townSquare(); break;
                        default: break;
                    }break;
                case "saveFromNorth":
                    switch (yourChoice){
                        case "c1": townGateNorth(); break;
                        default: break;
                    } break;
                case "townSquare":
                    switch (yourChoice){
                        case "c1": shop(); break;
                        case "c2": searchForHomeless(); break;
                        case "c3": towerGuard(); break;
                        case "c4": leaveTown(); break;
                        default:break;
                    }break;
                case "shop":
                    switch (yourChoice){
                        case "c1": weaponShop(); break;
                        case "c2": if(playerLvl<2){
                            tooLowLevelForConsumableShop(); break;
                        } else {
                            consumableShop(); break;
                        }
                        case "c3": townSquare(); break;
                        default: break;
                    }break;
                case "weaponShop":
                    switch (yourChoice){
                        case "c1": longSword(); break;
                        case "c2": spear(); break;
                        case "c3": katana(); break;
                        case "c4": wand(); break;
                        default: break;
                    }break;
                case "longSword":
                case "spear":
                case "katana":
                case "wand":
                case "tooLowLevelForConsumableShop":
                    switch (yourChoice){
                        case "c1": shop(); break;
                        default:break;
                    }break;
                case "consumableShop":
                    switch (yourChoice){
                        case "c1": instantHP(); break;
                        case "c2": plus5Damage(); break;
                        case "c3": apple(); break;
                        case "c4": shop(); break;
                        default: break;
                    } break;
                case "leaveTown":
                    switch (yourChoice){
                        case "c1": townGateNorth(); break;
                        case "c2": townGateSouth(); break;
                        case "c3": townSquare(); break;
                        default: break;
                    } break;
                case "townGateSouth":
                    switch (yourChoice){
                        case "c1": killedGoblin(); break;
                        case "c2": attackGuard2(); break;
                        case "c3": crossRoad2(); break;
                        case "c4": saveFromSouth(); break;
                        default: break;
                    }break;
                case "crossRoad2":
                    switch (yourChoice){
                        case "c1": townGateSouth(); break;
                        case "c2": if(ganondorfStaff==0){
                            east2();
                        } else {
                            enterGanondorfsCabin();
                        } break;
                        case "c3": if(raiderDefeated==0) {
                            raider();
                        } else {
                            townGate2();
                        } break;
                        case "c4": west2();break;
                        default: break;
                    } break;
                case "towerGuard":
                    switch (yourChoice){
                        case "c1": if(didTowerGuard!=0){
                            watchTower();
                        } else{
                            townSquare();
                        } break;
                        default: break;
                    }break;
                case "watchTower":
                    switch (yourChoice){
                        case "c1": searchDrawers(); break;
                        case "c2": enterCloset(); break;
                        case "c3": enterWeaponRoom(); break;
                        case "c4": townSquare(); break;
                        default: break;
                    }break;
                case "closet":
                    switch (yourChoice){
                        case "c1": searchClothes(); break;
                        case "c2": watchTower(); break;
                        default: break;
                    } break;
                case "weaponRoom":
                case "drawers":
                case "searchClothes":
                    switch (yourChoice){
                        case "c1": watchTower(); break;
                        default: break;
                    } break;
                case "enterGanondorfsCabin":
                case "endingLocked":
                case "eatFood":
                    switch (yourChoice){
                        case "c1": ganondorfsCabin(); break;
                        default:break;
                    }break;
                case "noRed":
                case "noYellow":
                case "noBlue":
                case "red":
                case "yellow":
                case "blue":
                    switch (yourChoice){
                        case "c1": lookAtPotions(); break;
                        default: break;
                    } break;
                case "east2":
                case "west2":
                case "winRaider":
                    switch (yourChoice){
                        case "c1": crossRoad2(); break;
                        default: break;
                    } break;
                case "raider":
                    switch (yourChoice){
                        case "c1": fight2(); break;
                        case "c2": crossRoad2(); break;
                        default:break;
                    }break;
                case "fight2":
                    switch (yourChoice){
                        case "c1": playerAttack2(); break;
                        case "c2": crossRoad2(); break;
                        default: break;
                    } break;
                case "playerAttack2":
                    switch (yourChoice){
                        case "c1":
                            if(raiderHP<=0){
                                winRaider();
                            } else {
                                monsterAttack2();
                            }break;
                        default: break;
                    } break;
                case "monsterAttack2":
                    switch (yourChoice){
                        case "c1": if(playerHP <=0){
                            lose();
                        } else {
                            fight2();
                        } break;
                    }break;
                case "instantHP":
                case "plus5Damage":
                case "apple":
                    switch (yourChoice){
                        case "c1": consumableShop(); break;
                        default: break;
                    } break;
                case "townGate2":
                    switch (yourChoice){
                        case "c1": if(kingSword==0){
                            talkGuard2();
                        } else {
                            passGuard2();
                        } break;
                        case "c2": attackGuard3(); break;
                        case "c3": crossRoad2(); break;
                        default:break;
                    }break;
                case "passGuard2":
                case "throneLocked":
                case "throne":
                case "tooLowLevelForDungeon":
                case "throneRoomDone":
                    switch (yourChoice){
                        case "c1": townSquare2(); break;
                        default: break;
                    } break;
                case "townSquare2":
                    switch (yourChoice){
                        case "c1": if(throneKey==0){
                            throneLocked();
                        } else{
                            if(throneRoomDone==0){
                                throne();
                            } else{
                                throneRoomDone();
                            }
                        }break;
                        case "c2": barracks(); break;
                        case "c3": if(playerLvl >=3){
                            dungeonEntrance();
                        } else {
                            tooLowLevelForDungeon();
                        }break;
                        case "c4": leaveTown2(); break;
                        default:break;
                    }break;
                case "dungeonEntrance":
                    switch (yourChoice){
                        case "c1": cells(); break;
                        case "c2": townSquare2(); break;
                        default: break;
                    } break;
                case "barracks":
                    switch (yourChoice){
                        case "c1": if(throneKey==0){
                            lockerRoom();
                        } else {
                            nothingInLockerRoom();
                        } break;
                        case "c2": if(questionAsked==0){
                            askQuestion();
                        } else {
                            noQuestions();
                        } break;
                        case "c3": townSquare2(); break;
                        default: break;
                    } break;
                case "askQuestion":
                    switch (yourChoice){
                        case "c1": askingQuestion(); break;
                        default:break;
                    } break;
                case "askingQuestion":
                    switch (yourChoice){
                        case "c1": questionAnswer(); break;
                        default:break;
                    } break;
                case "questionAnswer":
                case "noQuestions":
                case "lockerRoom":
                case "nothingInLockerRoom":
                    switch (yourChoice){
                        case "c1": barracks(); break;
                        default:break;
                    } break;
                case "cells":
                    switch (yourChoice){
                        case "c1": if(talkedToDungeonGuard==0){
                            talkToDungeonGuard();
                        } else {
                            dungeonGuardLeave();
                        } break;
                        case "c2": if(talkedToPrisoner==0){
                            if(prisonerFree==0){
                                talkToPrisoner1();
                            } else {
                                noPrisoner();
                            }
                        } else {
                            if(prisonerFree==0){
                                prisonerNoTalk();
                            } else {
                                noPrisoner();
                            }
                        } break;
                        case "c3": townSquare2(); break;
                        default:break;
                    } break;
                case "talkToPrisoner1":
                    switch (yourChoice){
                        case "c1": talkToPrisoner2(); break;
                        default: break;
                    } break;
                case "talkToPrisoner2":
                    switch (yourChoice){
                        case "c1": talkToPrisoner3(); break;
                        default: break;
                    } break;
                case "talkToPrisoner3":
                    switch (yourChoice){
                        case "c1": talkToPrisoner4(); break;
                        default: break;
                    } break;
                case "talkToPrisoner4":
                    switch (yourChoice){
                        case "c1": talkToPrisoner5(); break;
                        default: break;
                    } break;
                case "talkToPrisoner5":
                case "prisonerNoTalk":
                case "dungeonGuardLeave":
                case "notEnoughCoins":
                case "thanks":
                case "noPrisoner":
                    switch (yourChoice){
                        case "c1": cells(); break;
                        default: break;
                    } break;
                case "talkToDungeonGuard":
                    switch (yourChoice){
                        case "c1": setFree(); break;
                        case "c2": cells(); break;
                        default: break;
                    } break;
                case "setFree":
                    switch (yourChoice){
                        case "c1": if(coins<10000){
                            notEnoughCoins();
                        } else {
                            pay10000();
                        } break;
                        case "c2": cells(); break;
                        default: break;
                    } break;
                case "pay10000":
                    switch (yourChoice){
                        case "c1": thanks(); break;
                        default: break;
                    } break;
                case "leaveTown2":
                    switch (yourChoice){
                        case "c1": crossRoad2(); break;
                        case "c2": townSquare2(); break;
                        default: break;
                    } break;
                case "ganondorfsCabin":
                    switch (yourChoice){
                        case "c1": lookAtPotions(); break;
                        case "c2": lookAtBookshelf(); break;
                        case "c3": eatFood(); break;
                        case "c4": cabinExits(); break;
                        default: break;
                    } break;
                case "lookAtPotions":
                    switch (yourChoice){
                        case "c1": if(didRed==0){
                            red();
                        } else {
                            noRed();
                        } break;
                        case "c2": if(didYellow==0){
                            yellow();
                        } else {
                            noYellow();
                        } break;
                        case "c3": if(didBlue==0){
                            blue();
                        } else {
                            noBlue();
                        } break;
                        case "c4": ganondorfsCabin(); break;
                        default: break;
                    } break;
                case "lookAtBookshelf":
                    switch (yourChoice){
                        case "c1": if(basicSpellsRead==0){
                            basicSpells();
                        } else {
                            alreadyRead();
                        } break;
                        case "c2": if(complexSpellsRead==0){
                            complexSpells();
                        } else {
                            alreadyRead();
                        } break;
                        case "c3": if(elementalSpellsRead==0){
                            elementalSpells();
                        } else {
                            alreadyRead();
                        } break;
                        case "c4": ganondorfsCabin(); break;
                        default: break;
                    } break;
                case "cabinExits":
                    switch (yourChoice){
                        case "c1": crossRoad2(); break;
                        case "c2": if(superKey==2){
                            gameEnding();
                        } else{
                            endingLocked();
                        }
                        default: break;
                    } break;
                case "basicSpells":
                case "complexSpells":
                case "elementalSpells":
                    switch (yourChoice){
                        case "c1": lookAtBookshelf(); break;
                        default: break;
                    } break;
                case "alreadyRead":
                    switch (yourChoice){
                        case "c1": lookAtBookshelf(); break;
                        default: break;
                    } break;
            }
        }
    }
}
