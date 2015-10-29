/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planpath;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author YANDONG ZHANG && TIANYI HUANG
 */
public class PlanPath {

    TransitionRules rule = new TransitionRules();
    Scanner input = new Scanner(System.in);

    //initialise the variable
    String direction = " ";

    String algorithm = " ";   //select which algorithm do you want
    int iteration = 0;       //how many iteration for this algorithm
    int mapNumber = 0;      //save the area of map (N*N)
    ArrayList<String> map = new ArrayList<String>(); //save the information of map（R,X,S,G)

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PlanPath plan = new PlanPath();
        plan.readFromFile(args[0]);
        plan.WriteToFile(args[1]);

    }

    /*
     *   Read the  information from input text file
     */
    public void readFromFile(String FILENAME) {

        try {
            FileReader fileRead = new FileReader(FILENAME);
            Scanner parser = new Scanner(fileRead);
            algorithm = parser.nextLine();
            iteration = parser.nextInt();
            mapNumber = parser.nextInt();
            String blank = parser.nextLine();
            while (parser.hasNext()) {
                map.add(parser.nextLine());
            }

            fileRead.close();
        } catch (FileNotFoundException exception) {
            System.out.println(FILENAME + "not found.");

        } catch (IOException exception) {
            System.out.println("Unexception I/O exception occurs");
        }

    }

    /*
     ** Processing the input file and get the result to  another output file
     */
    public void WriteToFile(String OUTPUTTXTNAME) {
        try {
            PrintWriter outputFile = new PrintWriter(OUTPUTTXTNAME);

            //use the array to save the information of map
            char arrayMap[][] = new char[mapNumber][mapNumber];

            for (int i = 0; i < mapNumber; i++) {
                for (int j = 0; j < mapNumber; j++) {
                    arrayMap[i][j] = map.get(i).charAt(j);
                }
            }

            //select which algorithm you want
            switch (algorithm) {
                case "B": //BFS Algorithm
                {

                    String direction = " ";
                    String newCoordinate = " ";

                    //�initialise the list
                    ArrayList<String> outputOpen = new ArrayList<String>();
                    ArrayList<String> outputClosed = new ArrayList<String>();
                    ArrayList<String> outputLine1 = new ArrayList<String>();
                    ArrayList<String> outputLine2 = new ArrayList<String>();
                    ArrayList<String> outputLine3 = new ArrayList<String>();

                    ArrayList<Node> closed = new ArrayList<Node>();
                    ArrayList<Node> queue = new ArrayList<Node>();

                    //initial list to save the before List
                    ArrayList<ArrayList<Node>> Before = new ArrayList<ArrayList<Node>>();
                    ArrayList<Node> tmp = new ArrayList<Node>();
                    for (int i = 0; i < mapNumber; i++) {
                        tmp = new ArrayList<Node>();
                        for (int j = 0; j < mapNumber; j++) {
                            tmp.add(new Node(0, 0));
                        }
                        Before.add(tmp);
                    }

                    //find the coordinate "Start position" and "Goal Position"
                    boolean findS = false;
                    boolean findG = false;
                    int xS = 0;
                    int currentX = 0;
                    int currentY = 0;
                    int yS = 0;
                    int xG = 0;
                    int yG = 0;
                    char value = ' ';

                    while (findS == false || findG == false) {
                        for (int i = 0; i < mapNumber; i++) {
                            for (int j = 0; j < mapNumber; j++) {
                                value = arrayMap[i][j];
                                if (value == 'S') {
                                    xS = i;
                                    yS = j;
                                    findS = true;
                                }
                                if (value == 'G') {
                                    xG = i;
                                    yG = j;
                                    findG = true;
                                }

                            }
                        }

                    }

                    //change the informationg about G and S for the Transition Rules
                    arrayMap[xS][yS] = 'R';
                    arrayMap[xG][yG] = 'R';

                    int x = xS;
                    int y = yS;
                    String[] directionArray;

                    /*
                     **BFS algorithm begin
                     */
                    boolean firstTask = true;
                    boolean task = false; //identify weather the first step will be stop

                    Node node = new Node(x, y);
                    int count = 0;

                    queue.add(node);     //add Start to Queue
                    closed.add(node);

                    //2.loop queue to find a goal
                    while (!queue.isEmpty()) {
                        boolean once = true;
                        //get the first node in queue
                        currentX = queue.get(0).getX();
                        currentY = queue.get(0).getY();

                        if (currentX == xG && currentY == yG) {
                            break;
                        } //if not find a goal,continue loop
                        else {

                            queue.remove(0);
                            direction = rule.rules(currentX, currentY, mapNumber, arrayMap);

                            //identify weather the direction will be a "Null"
                            if (!queue.isEmpty() && direction == null) {
                                while (direction == null) {
                                    currentX = queue.get(0).getX();
                                    currentY = queue.get(0).getY();
                                    queue.remove(0);
                                    direction = rule.rules(currentX, currentY, mapNumber, arrayMap);
                                }

                            }

                            //identify the last step weather is "null"
                            if (queue.isEmpty() == true && direction == null) {
                                outputFile.println("Sorry,no path!!!");
                                task = false;
                                break;
                            } else {
                                //loop the direction from the TransitionRules
                                directionArray = direction.trim().split(" ");
                                for (String directionArray1 : directionArray) {

                                    newCoordinate = rule.move(currentX, currentY, directionArray1);

                                    String[] coordinateArray = new String[]{};
                                    coordinateArray = newCoordinate.trim().split(",");
                                    x = Integer.parseInt(coordinateArray[0]);
                                    y = Integer.parseInt(coordinateArray[1]);

                                    node = new Node(x, y);
                                    boolean inClosed = false;
                                    for (int i = 0; i < closed.size(); i++) {
                                        if (x == closed.get(i).getX() && y == closed.get(i).getY()) {
                                            inClosed = true;
                                        }
                                    }
                                    if (!inClosed) {

                                        //Strat to println "Open line"
                                        if (firstTask == true) {

                                            outputOpen.add("S-" + directionArray1.trim().toString());

                                        } else {

                                            if (once == true) {
                                                outputClosed.add(outputOpen.get(0));//new

                                                once = false;
                                                count++;
                                            }

                                            outputOpen.add(outputClosed.get(count) + "-" + directionArray1);

                                        }

                                        queue.add(node);
                                        closed.add(node);

                                        Before.get(x).get(y).setX(currentX);
                                        Before.get(x).get(y).setY(currentY);

                                    }
                                }
                                if (firstTask == true) {
                                    outputClosed.add("S");
                                } else {
                                    outputOpen.remove(0);
                                }
                            }

                        }

                        if (queue.size() == 0) {
                            outputFile.println("Sorry,no path!!!");
                            System.exit(0);
                        }

                        task = true;
                        firstTask = false;

                        String openLine = " ";
                        String closedLine = " ";
                        String allLine1 = " ";
                        String allLine2 = " ";
                        String allLine3 = " ";
                        String level = " ";
                        int time = 0;

                        //Print the open Line
                        for (int i = 0; i < outputOpen.size(); i++) {

                            openLine += " " + outputOpen.get(i).toString();

                        }

                        //Print the close Line
                        for (int i = 0; i < outputClosed.size(); i++) {

                            closedLine += " " + outputClosed.get(i).toString();
                        }

                        int number = outputClosed.size() - 1;
                        level = outputClosed.get(number).toString();

                        //print the path line cost
                        for (int i = 0; i < level.length(); i++) {
                            String same = level.substring(i, i + 1);
                            if (same.equals("-")) {
                                time++;
                            }
                        }

                        allLine1 = " ";
                        allLine2 = " ";
                        allLine3 = " ";

                        allLine1 = outputClosed.get(number).toString() + " " + time + " " + 0 + " " + time;
                        allLine2 = "OPEN " + openLine.trim();
                        allLine3 = "CLOSED " + closedLine.trim();

                        outputLine1.add(allLine1);
                        outputLine2.add(allLine2);
                        outputLine3.add(allLine3);

                        once = false;

                    }

                    if (queue.isEmpty() == true && currentX != xG && currentY != yG) {
                        outputFile.println("Sorry,no Path");
                        System.exit(0);
                    }

                    //backtrack the before node
                    if (task == true) {
                        x = xG;
                        y = yG;
                        String result = " ";
                        String getDirection = " ";
                        arrayMap[xS][yS] = 'S';
                        arrayMap[xG][yG] = 'G';

                        int xDifference = 0;
                        int yDifference = 0;
                        int totalCost = 0;

                        while (!((x == xS) && (y == yS))) {
                            int beforeX = Before.get(x).get(y).getX();
                            int beforeY = Before.get(x).get(y).getY();

                            xDifference = beforeX - x;
                            yDifference = beforeY - y;

                            getDirection = "-" + rule.returnDirection(xDifference, yDifference) + getDirection;
                            totalCost = totalCost + rule.cost(rule.returnDirection(xDifference, yDifference));

                            x = beforeX;
                            y = beforeY;

                        }

                        outputFile.print("\n" + "S" + getDirection.trim());
                        outputFile.println("-G" + " " + totalCost + "\n");

                        //print the result of iterations
                        if (iteration > outputLine1.size()) {
                            iteration = outputLine1.size();

                            for (int i = 0; i < iteration; i++) {
                                outputFile.println("\n" + outputLine1.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine2.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine3.get(i).toString() + "\n");
                            }
                        } else {
                            for (int i = 0; i < iteration; i++) {
                                outputFile.println("\n" + outputLine1.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine2.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine3.get(i).toString() + "\n");
                            }
                        }
                    }

                }
                ;
                break;
                case "D": //DFS Algorithm
                {

                    String direction = " ";
                    String newCoordinate = " ";

                    ArrayList<String> printOpen = new ArrayList<String>();
                    int g = 0;
                    int h = 0;
                    int f = 0;

                    ArrayList<String> outputLine1 = new ArrayList<String>();
                    ArrayList<String> outputLine2 = new ArrayList<String>();
                    ArrayList<String> outputLine3 = new ArrayList<String>();
                    ArrayList<String> outputOpen = new ArrayList<String>();
                    ArrayList<String> outputClosed = new ArrayList<String>();
                    ArrayList<String> outputAll = new ArrayList<String>();

                    ArrayList<Node> closed = new ArrayList<Node>(); 
                    ArrayList<Node> queue = new ArrayList<Node>();

                    ArrayList<ArrayList<Node>> Before = new ArrayList<ArrayList<Node>>();
                    ArrayList<Node> tmp = new ArrayList<Node>();
                    for (int i = 0; i < mapNumber; i++) {
                        tmp = new ArrayList<Node>();
                        for (int j = 0; j < mapNumber; j++) {
                            tmp.add(new Node(0, 0));
                        }
                        Before.add(tmp);
                    }

                    boolean findS = false;
                    boolean findG = false;
                    int xS = 0;
                    int currentX = 0;
                    int currentY = 0;
                    int yS = 0;
                    int xG = 0;
                    int yG = 0;
                    char value = ' ';

                    while (findS == false || findG == false) {
                        for (int i = 0; i < mapNumber; i++) {
                            for (int j = 0; j < mapNumber; j++) {
                                value = arrayMap[i][j];
                                if (value == 'S') {
                                    xS = i;
                                    yS = j;
                                    findS = true;
                                }
                                if (value == 'G') {
                                    xG = i;
                                    yG = j;
                                    findG = true;
                                }

                            }
                        }

                    }

                    arrayMap[xS][yS] = 'R';
                    arrayMap[xG][yG] = 'R';

                    int x = xS;
                    int y = yS;
                    String[] directionArray;

                    /*
                     **DFS algrothm start
                     */
                    boolean firstTask = true, expand = false;
                    boolean task = false; 
                    int lastIndex = 0;

                    Node node = new Node(x, y);
                    queue.add(node);    
                    closed.add(node);

                  //start loop
                    while (!queue.isEmpty()) {
                        boolean once = true;
                      
                        currentX = queue.get(queue.size() - 1).getX();
                        currentY = queue.get(queue.size() - 1).getY();

                        
                        //if find a goal ,then break
                        if (currentX == xG && currentY == yG) {
                            break;
                        } 
                        else {

                            expand = false;
                            direction = rule.rules(currentX, currentY, mapNumber, arrayMap);

                            ///Judge the first step is nill or not null
                            if (queue.size() == 1 && direction == null) {
                                outputFile.println("Sorry,no parth");
                                task = false; 
                                System.exit(0);
                            }

                           //identify weather the direction is null
                            directionArray = direction.trim().split(" ");
                            for (String directionArray1 : directionArray) {

                                newCoordinate = rule.move(currentX, currentY, directionArray1);

                                String[] coordinateArray = new String[]{};
                                coordinateArray = newCoordinate.trim().split(",");
                                x = Integer.parseInt(coordinateArray[0]);
                                y = Integer.parseInt(coordinateArray[1]);

                                node = new Node(x, y);
                                boolean inClosed = false;
                                for (int i = 0; i < closed.size(); i++) {
                                    if (x == closed.get(i).getX() && y == closed.get(i).getY()) {
                                        inClosed = true;
                                    }
                                }
                                if (!inClosed) {

                                    if (firstTask == true) {

                                        outputOpen.add("S-" + directionArray1.trim().toString());

                                    } else {

                                        if (once == true) {
                                            outputClosed.add(outputOpen.get(0));//new

                                            once = false;

                                        }

                                        outputOpen.add(outputClosed.get((outputClosed.size() - 1)) + "-" + directionArray1);

                                    }

                                    expand = true;
                                    queue.add(node);
                                    closed.add(node);
                                    Before.get(x).get(y).setX(currentX);
                                    Before.get(x).get(y).setY(currentY);
                                    break;
                                }
                            }
                        }
                        if (!expand) {
                            queue.remove(queue.size() - 1);
                        }

                        if (firstTask == true) {
                            //  outputClosed.add("S");
                        } else {

                            outputOpen.remove(0);

                        }

                        if (queue.size() == 0) {
                            outputFile.println("Sorry,no path");
                            System.exit(0);
                            break;
                        }

                        task = true;
                        //Create the variable of print
                        String openLine = " ";
                        String closedLine = " ";
                        String allLine = " ";
                        String level = " ";
                        int time = 0;

                        //打印OPEN列表
                        for (int i = 0; i < outputOpen.size(); i++) {

                            openLine += " " + outputOpen.get(i).toString();

                        }

                       //print open line
                        if (firstTask == true) {
                            outputClosed.add("S");
                            closedLine = "S";
                        } else {
                            for (int i = 0; i < outputClosed.size(); i++) {

                                closedLine += " " + outputClosed.get(i).toString();
                            }
                        }

                        int number = outputClosed.size() - 1;
                        level = outputClosed.get(number).toString();

                       //print the cost of g
                        for (int i = 0; i < level.length(); i++) {
                            String same = level.substring(i, i + 1);
                            if (same.equals("-")) {
                                time++;
                            }
                        }

                        String allLine1 = " ";
                        String allLine2 = " ";
                        String allLine3 = " ";

                        allLine1 = outputClosed.get(number).toString() + " " + time + " " + 0 + " " + time;
                        allLine2 = "OPEN " + openLine.trim();
                        allLine3 = "CLOSED " + closedLine.trim();

                        outputLine1.add(allLine1);
                        outputLine2.add(allLine2);
                        outputLine3.add(allLine3);

                        once = false;
                        firstTask = false;
                    }

                    if (queue.isEmpty() == true && currentX != xG && currentY != yG) {
                        outputFile.println("Sorry,no Path");
                    }

                //backtrack the before node
                    if (task == true) {
                        x = xG;
                        y = yG;
                        String result = " ";
                        String getDirection = " ";
                        arrayMap[xS][yS] = 'S';
                        arrayMap[xG][yG] = 'G';

                        int xDifference = 0;
                        int yDifference = 0;
                        int totalCost = 0;

                        while (!((x == xS) && (y == yS))) {
                            int beforeX = Before.get(x).get(y).getX();
                            int beforeY = Before.get(x).get(y).getY();

                            xDifference = beforeX - x;
                            yDifference = beforeY - y;

                            getDirection = "-" + rule.returnDirection(xDifference, yDifference) + getDirection;
                            totalCost = totalCost + rule.cost(rule.returnDirection(xDifference, yDifference));

                            x = beforeX;
                            y = beforeY;

                        }
                        outputFile.print("\n" + "S" + getDirection.trim());
                        outputFile.println("-G" + " " + totalCost + "\n");

                      //print the result of interation
                        if (iteration > outputLine1.size()) {
                            iteration = outputLine1.size();

                            for (int i = 0; i < iteration; i++) {
                                outputFile.println("\n" + outputLine1.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine2.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine3.get(i).toString() + "\n");
                            }
                        } else {
                            for (int i = 0; i < iteration; i++) {
                                outputFile.println("\n" + outputLine1.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine2.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine3.get(i).toString() + "\n");
                            }
                        }
                    }

                }
                break;
                    //Uniform cost search alogrithm start 
                case "U": 
                {
                    Cost cost = new Cost();

                    String direction = " ";
                    String result = " ";
                    String newCoordinate = " ";
                    int printCurrentG = 0; //print the G cost

                    ArrayList<String> printOpen = new ArrayList<String>();
                    int g = 0;
                    int h = 0;
                    int f = 0;

                    ArrayList<String> outputLine1 = new ArrayList<String>();
                    ArrayList<String> outputLine2 = new ArrayList<String>();
                    ArrayList<String> outputLine3 = new ArrayList<String>();
                    ArrayList<String> outputOpen = new ArrayList<String>();
                    ArrayList<String> outputClosed = new ArrayList<String>();

                    ArrayList<NodeG> closed = new ArrayList<NodeG>(); 
                    ArrayList<NodeG> queueG = new ArrayList<NodeG>(); 

                      // used to initialize Before list
                    ArrayList<ArrayList<Node>> Before = new ArrayList<ArrayList<Node>>();
                    ArrayList<Node> tmp = new ArrayList<Node>();            
                    for (int i = 0; i < mapNumber; i++) {
                        tmp = new ArrayList<Node>();
                        for (int j = 0; j < mapNumber; j++) {
                            tmp.add(new Node(0, 0));
                        }
                        Before.add(tmp);
                    }

                    boolean findS = false;
                    boolean findG = false;
                    int xS = 0;
                    int currentX = 0;
                    int currentY = 0;
                    int yS = 0;
                    int xG = 0;
                    int yG = 0;
                    char value = ' ';

                    while (findS == false || findG == false) {
                        for (int i = 0; i < mapNumber; i++) {
                            for (int j = 0; j < mapNumber; j++) {
                                value = arrayMap[i][j];
                                if (value == 'S') {
                                    xS = i;
                                    yS = j;
                                    findS = true;
                                }
                                if (value == 'G') {
                                    xG = i;
                                    yG = j;
                                    findG = true;
                                }

                            }
                        }

                    }

                    arrayMap[xS][yS] = 'R';
                    arrayMap[xG][yG] = 'R';

                    int x = xS;
                    int y = yS;
                    String[] directionArray;

                    //use the comparator to find the node with minimun g value
                    Comparator<NodeG> OrderExpand = new Comparator<NodeG>() {

                        @Override
                        public int compare(NodeG node1, NodeG node2) {
                            int nodeCost1 = node1.getG();
                            int nodeCost2 = node2.getG();
                            if (nodeCost2 < nodeCost1) {
                                return 1;
                            } else if (nodeCost2 > nodeCost1) {
                                return -1;
                            } else {
                                return 0;
                            }
                        }
                    };
                    Queue<NodeG> priorityQueue = new PriorityQueue<NodeG>(100, OrderExpand);

                    Cost ucsg = new Cost();//calucate every step about G
                    boolean firstTask = true; //identify the first stpe is ok or not
                    boolean task = false; 
                    NodeG nodeG = new NodeG(x, y, g);
                    queueG.add(nodeG);     //add Start to Queue

            
                    while (!queueG.isEmpty()) {
                        boolean once = true; //when printing thr open list use one closed list times
                 
                        if (firstTask == true) {
                            currentX = queueG.get(0).getX();
                            currentY = queueG.get(0).getY();

                            closed.add(queueG.get(0));
                            queueG.remove(0);

                        } else {

                         //selected the value with the minmum g value to expand
                            for (int i = 0; i < queueG.size(); i++) {
                                boolean inClosed = false;
                                for (int j = 0; j < closed.size(); j++) {
                                    if (closed.get(j).getX() == queueG.get(i).getX()
                                            && closed.get(j).getY() == queueG.get(i).getY()) {
                                        inClosed = true;
                                    }
                                }
                                if (!inClosed) {
                                    priorityQueue.add(queueG.get(i));
                                }

                            }

                            String coordinateG = " ";

                        //if find null ,then will return the no path
                            try {
                                coordinateG = priorityQueue.poll().getCoordinate();
                            } catch (NullPointerException e) {

                                outputFile.println("Sorry, no path!!! ");
                                break;
                            }

                            String[] coordinateGArray = new String[]{};

                            coordinateGArray = coordinateG.trim().split(",");
                            currentX = Integer.parseInt(coordinateGArray[0]);
                            currentY = Integer.parseInt(coordinateGArray[1]);

                           //when the node expand ,the put the node into closed listed
                            for (int i = 0; i < queueG.size(); i++) {
                                if (queueG.get(i).getX() == currentX && queueG.get(i).getY() == currentY) {

                                    closed.add(queueG.get(i));

                                    break;
                                }

                            }

                            for (int i = 0; i < queueG.size(); i++) {
                                if (queueG.get(i).getX() == currentX && queueG.get(i).getY() == currentY) {

                                    int gValue = queueG.get(i).getG();
                                    break;
                                }

                            }

                        }

                        if (currentX == xG && currentY == yG) {
                            task = true;
                            break;
                        } 
                        else {

                            direction = rule.rules(currentX, currentY, mapNumber, arrayMap);

                           
                            if (!queueG.isEmpty() && direction == null) {

                                while (direction == null) {

                                    for (int i = 0; i < queueG.size(); i++) {
                                        priorityQueue.add(queueG.get(i));
                                    }

                                    String coordinateG = priorityQueue.poll().getCoordinate();
                                    String[] coordinateGArray = new String[]{};

                                    coordinateGArray = coordinateG.trim().split(",");
                                    currentX = Integer.parseInt(coordinateGArray[0]);
                                    currentY = Integer.parseInt(coordinateGArray[1]);

                                    for (int i = 0; i < queueG.size(); i++) {
                                        if (queueG.get(i).getX() == currentX && queueG.get(i).getY() == currentY) {
                                            closed.add(queueG.get(i));
                                            queueG.remove(i);
                                            break;
                                        }

                                    }

                                    direction = rule.rules(currentX, currentY, mapNumber, arrayMap);

                                }

                            }

                        
                            if (queueG.isEmpty() == true && direction == null) {
                                outputFile.println("Sorry,no Path!!!");
                                task = false; 
                                break;
                            } else {

                                directionArray = direction.trim().split(" ");

                              //save the current g cost
                                int currentNodeG = 0; 

                                for (int i = 0; i < queueG.size(); i++) {
                                    if (queueG.get(i).getX() == currentX && queueG.get(i).getY() == currentY) {
                                        currentNodeG = queueG.get(i).getG();

                                        break;
                                    }
                                }
                                  //expand the new node
                                for (String directionArray1 : directionArray) {

                                    newCoordinate = rule.move(currentX, currentY, directionArray1);
                                    String[] coordinateArray = new String[]{};
                                    coordinateArray = newCoordinate.trim().split(",");
                                    x = Integer.parseInt(coordinateArray[0]);
                                    y = Integer.parseInt(coordinateArray[1]);

                                    boolean inClosed = false;

                                    for (int i = 0; i < closed.size(); i++) {
                                        if (x == closed.get(i).getX() && y == closed.get(i).getY()) {
                                            inClosed = true;
                                        }
                                    }
                                    if (!inClosed) {
                                        //add the new node int the queue
                                        int newNodeG = (ucsg.gCost(directionArray1, currentNodeG));

                                        if (firstTask == true) {

                                            outputOpen.add("S-" + directionArray1.trim().toString());

                                        } else {

                                            if (once == true) {
                                                outputClosed.add(outputOpen.get(0));//new

                                                once = false;

                                            }

                                            outputOpen.add(outputClosed.get((outputClosed.size() - 1)) + "-" + directionArray1);

                                        }

                                        //generate a new node
                                        nodeG = new NodeG(x, y, newNodeG);

                                        queueG.add(nodeG);

                                        Before.get(x).get(y).setX(currentX);
                                        Before.get(x).get(y).setY(currentY);

                                    }
                                }

                            }

                        }

                        if (firstTask == true) {
                            //  outputClosed.add("S");
                        } else {

                            outputOpen.remove(0);

                        }

                        String openLine = " ";
                        String closedLine = " ";
                        String allLine = " ";
                        String level = " ";
                        int time = 0;

                        //print the open list result
                        for (int i = 0; i < outputOpen.size(); i++) {

                            openLine += " " + outputOpen.get(i).toString();

                        }

                        //print the closed list
                        if (firstTask == true) {
                            outputClosed.add("S");
                            closedLine = "S";
                        } else {
                            for (int i = 0; i < outputClosed.size(); i++) {

                                closedLine += " " + outputClosed.get(i).toString();
                            }
                        }

                        int number = outputClosed.size() - 1;
                        String calucateG = outputClosed.get(number).toString();
                        int printG = 0;

                        String[] calucateGArray = new String[]{};
                        calucateGArray = calucateG.trim().split("-");

                        for (int i = 0; i < calucateGArray.length; i++) {
                            printG = cost.gCost(calucateGArray[i], printG);
                        }

                        String allLine1 = " ";
                        String allLine2 = " ";
                        String allLine3 = " ";

                        allLine1 = outputClosed.get(number).toString() + " " + printG + " " + 0 + " " + printG;
                        allLine2 = "OPEN " + openLine.trim();
                        allLine3 = "CLOSED " + closedLine.trim();

                        outputLine1.add(allLine1);
                        outputLine2.add(allLine2);
                        outputLine3.add(allLine3);

                        once = false;
                        firstTask = false;

                    }

                   
                    if (queueG.isEmpty() == true && currentX != xG && currentY != yG) {
                        outputFile.println("Sorry,no Path");
                    }

                    if (task == true) {
                        x = xG;
                        y = yG;

                        String getDirection = " ";
                        arrayMap[xS][yS] = 'S';
                        arrayMap[xG][yG] = 'G';

                        int xDifference = 0;
                        int yDifference = 0;
                        int totalCost = 0;

                        while (!((x == xS) && (y == yS))) {
                            int beforeX = Before.get(x).get(y).getX();
                            int beforeY = Before.get(x).get(y).getY();

                            xDifference = beforeX - x;
                            yDifference = beforeY - y;

                            getDirection = "-" + rule.returnDirection(xDifference, yDifference) + getDirection;
                            totalCost = totalCost + rule.cost(rule.returnDirection(xDifference, yDifference));

                            x = beforeX;
                            y = beforeY;

                        }

                        outputFile.print("\n" + "S" + getDirection.trim());
                        outputFile.println("-G" + " " + totalCost + "\n");

                        if (iteration > outputLine1.size()) {
                            iteration = outputLine1.size();

                            for (int i = 0; i < iteration; i++) {
                                outputFile.println("\n" + outputLine1.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine2.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine3.get(i).toString() + "\n");
                            }
                        } else {
                            for (int i = 0; i < iteration; i++) {
                                outputFile.println("\n" + outputLine1.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine2.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine3.get(i).toString() + "\n");
                            }

                        }

                    }

                }
                break;
                case "A": //A* Algorithm
                {

                    Cost cost = new Cost();

                    String direction = " ";
                    String result = " ";
                    String newCoordinate = " ";
                    double printH = 0;
                    ArrayList<String> printOpen = new ArrayList<String>();
                    int g = 0;
                    int h = 0;
                    int f = 0;

                    ArrayList<String> outputOpen = new ArrayList<String>();
                    ArrayList<String> outputClosed = new ArrayList<String>();
                    ArrayList<String> outputLine1 = new ArrayList<String>();
                    ArrayList<String> outputLine2 = new ArrayList<String>();
                    ArrayList<String> outputLine3 = new ArrayList<String>();

                    ArrayList<NodeF> closed = new ArrayList<NodeF>(); 
                    ArrayList<NodeF> queueF = new ArrayList<NodeF>(); 

                    ArrayList<ArrayList<Node>> Before = new ArrayList<ArrayList<Node>>();
                    ArrayList<Node> tmp = new ArrayList<Node>();              // used to initialize Before list
                    for (int i = 0; i < mapNumber; i++) {
                        tmp = new ArrayList<Node>();
                        for (int j = 0; j < mapNumber; j++) {
                            tmp.add(new Node(0, 0));
                        }
                        Before.add(tmp);
                    }

                    boolean findS = false;
                    boolean findG = false;
                    int xS = 0;
                    int currentX = 0;
                    int currentY = 0;
                    int yS = 0;
                    int xG = 0;
                    int yG = 0;
                    char value = ' ';

                    while (findS == false || findG == false) {
                        for (int i = 0; i < mapNumber; i++) {
                            for (int j = 0; j < mapNumber; j++) {
                                value = arrayMap[i][j];
                                if (value == 'S') {
                                    xS = i;
                                    yS = j;
                                    findS = true;
                                }
                                if (value == 'G') {
                                    xG = i;
                                    yG = j;
                                    findG = true;
                                }

                            }
                        }

                    }

                    arrayMap[xS][yS] = 'R';
                    arrayMap[xG][yG] = 'R';

                    int x = xS;
                    int y = yS;
                    String[] directionArray;

                    /*
                     ** find the node with the minimum f value
                     */
             
                    Comparator<NodeF> OrderExpand = new Comparator<NodeF>() {

                        @Override
                        public int compare(NodeF node1, NodeF node2) {
                            double nodeCost1 = node1.getF();
                            double nodeCost2 = node2.getF();
                            if (nodeCost2 < nodeCost1) {
                                return 1;
                            } else if (nodeCost2 > nodeCost1) {
                                return -1;
                            } else {
                                return 0;
                            }
                        }
                    };
                    Queue<NodeF> priorityQueue = new PriorityQueue<NodeF>(100, OrderExpand);

                    Cost gValue = new Cost();
                    Heuristic heuristic = new Heuristic(); // 

                    boolean firstTask = true; 

                    boolean task = false; 
                    NodeF nodeF = new NodeF(x, y, f);

                    queueF.add(nodeF);     //add Start to Queue

                    
                    while (!queueF.isEmpty()) {

                        boolean once = true;
                   
                        if (firstTask == true) {
                     
                            currentX = queueF.get(0).getX();
                            currentY = queueF.get(0).getY();

                            closed.add(queueF.get(0));
                         //put the first expand node into closed list
                            queueF.remove(0);
                         //then remove if form queueF

                        } else {

                            //select the minimun f value of node
                            for (int i = 0; i < queueF.size(); i++) {
                                boolean inClosed = false;
                                for (int j = 0; j < closed.size(); j++) {
                                    if (closed.get(j).getX() == queueF.get(i).getX()
                                            && closed.get(j).getY() == queueF.get(i).getY()) {
                                        inClosed = true;
                                    }
                                }
                                if (!inClosed) {
                                    priorityQueue.add(queueF.get(i));
                                }

                            }

                            String coordinateF = " ";

                            try {
                                coordinateF = priorityQueue.poll().getCoordinate();
                            } catch (NullPointerException e) {

                                System.out.println("Sorry, no path!!! ");
                                break;
                            }

                            String[] coordinateFArray = new String[]{};

                            coordinateFArray = coordinateF.trim().split(",");
                            currentX = Integer.parseInt(coordinateFArray[0]);
                            currentY = Integer.parseInt(coordinateFArray[1]);

                            printH = heuristic.heuristic(currentX, currentY, xG, yG);

                            for (int i = 0; i < queueF.size(); i++) {
                                if (queueF.get(i).getX() == currentX && queueF.get(i).getY() == currentY) {

                                    closed.add(queueF.get(i));

                                    break;
                                }

                            }

                        }

                        if (currentX == xG && currentY == yG) {
                            task = true;
                            break;
                        }
                        else {

                            direction = rule.rules(currentX, currentY, mapNumber, arrayMap);

                            if (!queueF.isEmpty() && direction == null) {

                                while (direction == null) {

                                    for (int i = 0; i < queueF.size(); i++) {
                                        priorityQueue.add(queueF.get(i));
                                    }

                                    String coordinateF = priorityQueue.poll().getCoordinate();
                                    String[] coordinateFArray = new String[]{};

                                    coordinateFArray = coordinateF.trim().split(",");
                                    currentX = Integer.parseInt(coordinateFArray[0]);
                                    currentY = Integer.parseInt(coordinateFArray[1]);

                                    for (int i = 0; i < queueF.size(); i++) {
                                        if (queueF.get(i).getX() == currentX && queueF.get(i).getY() == currentY) {
                                            closed.add(queueF.get(i));
                                            queueF.remove(i);
                                            break;
                                        }

                                    }

                                    direction = rule.rules(currentX, currentY, mapNumber, arrayMap);

                                }

                            }

                            if (queueF.isEmpty() == true && direction == null) {
                                System.out.println("Sorry,no Path!!!");
                                task = false;
                                break;
                            } else {

                                directionArray = direction.trim().split(" ");

                                //serach the G value and H value
                                int currentGValue = 0; 
                                int newGValue = 0;
                                double hValue = 0; 
                                double fValue = 0; 
                                //get the g value 
                                for (int i = 0; i < queueF.size(); i++) {
                                    if (queueF.get(i).getX() == currentX && queueF.get(i).getY() == currentY) {
                                        currentGValue = queueF.get(i).getG();
                                        break;
                                    }
                                }

                                //start to expand the node 
                                for (String directionArray1 : directionArray) {

                                    newCoordinate = rule.move(currentX, currentY, directionArray1);
                                    String[] coordinateArray = new String[]{};
                                    coordinateArray = newCoordinate.trim().split(",");
                                    x = Integer.parseInt(coordinateArray[0]);
                                    y = Integer.parseInt(coordinateArray[1]);

                                    //get the g value of current node
                                    hValue = heuristic.heuristic(x, y, xG, yG);
                                    boolean inClosed = false;

                                    for (int i = 0; i < closed.size(); i++) {
                                        if (x == closed.get(i).getX() && y == closed.get(i).getY()) {
                                            inClosed = true;
                                        }
                                    }
                                    if (!inClosed) {

                                        if (firstTask == true) {

                                            outputOpen.add("S-" + directionArray1.trim().toString());

                                        } else {

                                            if (once == true) {
                                                outputClosed.add(outputOpen.get(0));//new

                                                once = false;

                                            }

                                            outputOpen.add(outputClosed.get((outputClosed.size() - 1)) + "-" + directionArray1);

                                        }

                                        //calucate the new g value
                                        newGValue = (gValue.gCost(directionArray1, currentGValue));

                                        //calucate the new f value
                                        fValue = hValue + newGValue;

                                        //get a new node
                                        nodeF = new NodeF(x, y, fValue);

                                        queueF.add(nodeF);

                                        Before.get(x).get(y).setX(currentX);
                                        Before.get(x).get(y).setY(currentY);

                                    }
                                }

                            }

                        }

                        if (firstTask == true) {
                            //  outputClosed.add("S");
                        } else {

                            outputOpen.remove(0);

                        }

                        String openLine = " ";
                        String closedLine = " ";
                        String allLine = " ";
                        String level = " ";
                        int time = 0;

                        for (int i = 0; i < outputOpen.size(); i++) {

                            openLine += " " + outputOpen.get(i).toString();

                        }

                        if (firstTask == true) {
                            outputClosed.add("S");
                            closedLine = "S";
                        } else {
                            for (int i = 0; i < outputClosed.size(); i++) {

                                closedLine += " " + outputClosed.get(i).toString();
                            }
                        }

                        int number = outputClosed.size() - 1;
                        String calucateG = outputClosed.get(number).toString();

                        int printG = 0;

                        String[] calucateGArray = new String[]{};

                        calucateGArray = calucateG.trim().split("-");

                        for (int i = 0; i < calucateGArray.length; i++) {
                            printG = cost.gCost(calucateGArray[i], printG);
                        }

                        double printF = printG + printH;

                        String allLine1 = " ";
                        String allLine2 = " ";
                        String allLine3 = " ";

                        allLine1 = outputClosed.get(number).toString() + " " + printG + " " + String.format("%.2f", printH) + " " + String.format("%.2f", printF);
                        allLine2 = "OPEN " + openLine.trim();
                        allLine3 = "CLOSED " + closedLine.trim();

                        outputLine1.add(allLine1);
                        outputLine2.add(allLine2);
                        outputLine3.add(allLine3);

                        firstTask = false;
                        once = false;

                    }

                    if (queueF.isEmpty() == true && currentX != xG && currentY != yG) {
                        System.out.println("Sorry,no Path");
                    }

                    if (task == true) {
                        x = xG;
                        y = yG;

                        String getDirection = " ";
                        arrayMap[xS][yS] = 'S';
                        arrayMap[xG][yG] = 'G';

                        int xDifference = 0;
                        int yDifference = 0;
                        int totalCost = 0;

                        while (!((x == xS) && (y == yS))) {
                            int beforeX = Before.get(x).get(y).getX();
                            int beforeY = Before.get(x).get(y).getY();

                            xDifference = beforeX - x;
                            yDifference = beforeY - y;

                            getDirection = "-" + rule.returnDirection(xDifference, yDifference) + getDirection;
                            totalCost = totalCost + rule.cost(rule.returnDirection(xDifference, yDifference));

                            x = beforeX;
                            y = beforeY;

                        }

                        outputFile.print("\n" + "S" + getDirection.trim());
                        outputFile.println("-G" + " " + totalCost + "\n");

                        if (iteration > outputLine1.size()) {
                            iteration = outputLine1.size();

                            for (int i = 0; i < iteration; i++) {
                                outputFile.println("\n" + outputLine1.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine2.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine3.get(i).toString() + "\n");
                            }
                        } else {
                            for (int i = 0; i < iteration; i++) {
                                outputFile.println("\n" + outputLine1.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine2.get(i).toString() + "\n");
                                outputFile.println("\n" + outputLine3.get(i).toString() + "\n");
                            }
                        }

                    }

                }
                break;
            }

            ///////////////////////////////////////////////////////////////////////////////////////
            outputFile.close();

        } catch (IOException e) {

        }

    }

}
