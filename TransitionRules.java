/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planpath;

import java.util.ArrayList;

/**
 *
 * @author TIANYI HUANG
 */
public class TransitionRules {

    /*
     ****   Transition Rules
     */
    public String rules(int x, int y, int mapNumber, char[][] arrayMap) {

        //Initialise the va
        String direction = " "; //save the direction

        //when the point is on left top coner
        if (x == 0 && y == 0) {

            if (arrayMap[x][y + 1] == 'R' && arrayMap[x + 1][y + 1] == 'R' && arrayMap[x + 1][y] == 'R') {
                direction = "R RD D";
            }

            if (arrayMap[x + 1][y + 1] == 'X') {
                direction = "R D";
            }

            if (arrayMap[x + 1][y] == 'X' && arrayMap[x][y + 1] == 'R') {
                direction = "R";
            }

            if (arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'R') {
                direction = "D";
            }

            if (arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'X') {
                direction = null;
            }

        }

        //when the position on the top line
        if (x == 0 && y != 0 && y != mapNumber - 1) {

            if (arrayMap[x + 1][y] == 'X') {

                if (arrayMap[x][y - 1] == 'R' && arrayMap[x][y + 1] == 'R') {
                    direction = "R L";
                }

                if (arrayMap[x][y - 1] == 'R' && arrayMap[x][y + 1] == 'X') {
                    direction = "L";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x][y + 1] == 'R') {
                    direction = "R";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x][y + 1] == 'X') {
                    direction = null;
                }

            }

            if (arrayMap[x + 1][y] == 'R' && arrayMap[x][y + 1] == 'X') {

                if (arrayMap[x][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'R') {

                    direction = "D LD L";
                }

                if (arrayMap[x][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'X') {

                    direction = "D L";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x + 1][y - 1] == 'R') {

                    direction = "D";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x + 1][y - 1] == 'X') {
                    direction = "D";
                }
            }

            if (arrayMap[x + 1][y] == 'R' && arrayMap[x][y + 1] == 'R') {

                if (arrayMap[x][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D LD L";
                }

                if (arrayMap[x][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D LD L";
                }

                if (arrayMap[x][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD LD L";
                }

                if (arrayMap[x][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D L";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D";
                }
            }

        }

        //When the position on the top right corner
        if (x == 0 && y == mapNumber - 1) {
            if (arrayMap[x][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y] == 'R') {
                direction = "D LD L";
            }

            if (arrayMap[x][y - 1] == 'R' && arrayMap[x + 1][y] == 'X') {
                direction = "L";
            }

            if (arrayMap[x][y - 1] == 'X' && arrayMap[x + 1][y] == 'X') {
                direction = null;
            }

            if (arrayMap[x][y - 1] == 'X' && arrayMap[x + 1][y] == 'R') {
                direction = "D";
            }

            if (arrayMap[x][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y] == 'R') {
                direction = "D L";
            }

        }

        //when the position is on the left line
        if (x != 0 && x != mapNumber - 1 && y == 0) {

            if (arrayMap[x][y + 1] == 'X') {
                if (arrayMap[x - 1][y] == 'R' && arrayMap[x + 1][y] == 'R') {
                    direction = "D U";
                }

                if (arrayMap[x - 1][y] == 'R' && arrayMap[x + 1][y] == 'X') {
                    direction = "U";
                }

                if (arrayMap[x - 1][y] == 'X' && arrayMap[x + 1][y] == 'R') {
                    direction = "D";
                }

                if (arrayMap[x - 1][y] == 'X' && arrayMap[x + 1][y] == 'X') {
                    direction = null;
                }
            }

            if (arrayMap[x - 1][y] == 'X' && arrayMap[x][y + 1] == 'R') {
                if (arrayMap[x + 1][y] == 'R' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D";
                }

                if (arrayMap[x + 1][y] == 'X' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R";
                }

                if (arrayMap[x + 1][y] == 'R' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D";
                }

                if (arrayMap[x + 1][y] == 'X' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R";
                }
            }

            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'R') {
                if (arrayMap[x - 1][y + 1] == 'X' && arrayMap[x + 1][y] == 'R' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D U ";
                }

                if (arrayMap[x - 1][y + 1] == 'X' && arrayMap[x + 1][y] == 'X' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R U";
                }

                if (arrayMap[x - 1][y + 1] == 'X' && arrayMap[x + 1][y] == 'R' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D U";
                }

                if (arrayMap[x - 1][y + 1] == 'X' && arrayMap[x + 1][y] == 'X' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R U";
                }

                if (arrayMap[x - 1][y + 1] == 'R' && arrayMap[x + 1][y] == 'R' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D U RU";
                }

                if (arrayMap[x - 1][y + 1] == 'R' && arrayMap[x + 1][y] == 'X' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R U RU";
                }

                if (arrayMap[x - 1][y + 1] == 'R' && arrayMap[x + 1][y] == 'R' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D U RU";
                }

                if (arrayMap[x - 1][y + 1] == 'R' && arrayMap[x + 1][y] == 'X' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R U RU";
                }

            }

        }

        //when the position on the central
        if (x != 0 && x != mapNumber - 1 && y != 0 && y != mapNumber - 1) {

            if (arrayMap[x - 1][y] == 'X' && arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'X' && arrayMap[x][y - 1] == 'X') {
                direction = null;
            }

            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'X' && arrayMap[x][y - 1] == 'X') {
                direction = "U";
            }

            if (arrayMap[x - 1][y] == 'X' && arrayMap[x][y + 1] == 'R' && arrayMap[x + 1][y] == 'X' && arrayMap[x][y - 1] == 'X') {
                direction = "R";
            }

            if (arrayMap[x - 1][y] == 'X' && arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'R' && arrayMap[x][y - 1] == 'X') {
                direction = "D";
            }

            if (arrayMap[x - 1][y] == 'X' && arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'X' && arrayMap[x][y - 1] == 'R') {
                direction = "L";
            }

            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'R' && arrayMap[x][y - 1] == 'X') {
                direction = "D U";
            }

            if (arrayMap[x - 1][y] == 'X' && arrayMap[x][y + 1] == 'R' && arrayMap[x + 1][y] == 'X' && arrayMap[x][y - 1] == 'R') {
                direction = "R L";
            }

            if (arrayMap[x - 1][y] == 'X' && arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'R' && arrayMap[x][y - 1] == 'R') {

                if (arrayMap[x + 1][y - 1] == 'R') {
                    direction = "D LD L";
                }

                if (arrayMap[x + 1][y - 1] == 'X') {
                    direction = "D L";
                }

            }

            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'X' && arrayMap[x][y - 1] == 'R') {
                if (arrayMap[x - 1][y - 1] == 'R') {
                    direction = "L LU U";
                }

                if (arrayMap[x - 1][y - 1] == 'X') {
                    direction = "L U";
                }

            }

            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'R' && arrayMap[x + 1][y] == 'X' && arrayMap[x][y - 1] == 'X') {
                if (arrayMap[x - 1][y + 1] == 'R') {
                    direction = "R U RU";
                }

                if (arrayMap[x - 1][y + 1] == 'X') {
                    direction = "R U";
                }

            }

            if (arrayMap[x - 1][y] == 'X' && arrayMap[x][y + 1] == 'R' && arrayMap[x + 1][y] == 'R' && arrayMap[x][y - 1] == 'X') {

                if (arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D";
                }

                if (arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D";
                }

            }

            //9
            if (arrayMap[x - 1][y] == 'X' && arrayMap[x][y + 1] == 'R' && arrayMap[x + 1][y] == 'R' && arrayMap[x][y - 1] == 'R') {

                if (arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D LD L";
                }

                if (arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D LD L";
                }

                if (arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D L";
                }

                if (arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D L";
                }

            }

            //10
            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'X' && arrayMap[x + 1][y] == 'R' && arrayMap[x][y - 1] == 'R') {

                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'R') {
                    direction = "R LD L LU U";
                }

                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x + 1][y - 1] == 'X') {
                    direction = "D L LU U";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x + 1][y - 1] == 'R') {
                    direction = "D LD L U";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x + 1][y - 1] == 'X') {
                    direction = "D L U";
                }

            }

            //11
            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'R' && arrayMap[x + 1][y] == 'X' && arrayMap[x][y - 1] == 'R') {

                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x - 1][y + 1] == 'R') {
                    direction = "R L LU U RU";
                }

                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x - 1][y + 1] == 'X') {
                    direction = "R L LU U";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x - 1][y + 1] == 'R') {
                    direction = "R L U RU";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x - 1][y + 1] == 'X') {
                    direction = "R L U";
                }

            }

            //12
            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'R' && arrayMap[x + 1][y] == 'R' && arrayMap[x][y - 1] == 'X') {

                if (arrayMap[x - 1][y + 1] == 'R' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D U RU";
                }

                if (arrayMap[x - 1][y + 1] == 'R' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D U RU";
                }

                if (arrayMap[x - 1][y + 1] == 'X' && arrayMap[x + 1][y + 1] == 'R') {
                    direction = "R RD D U";
                }

                if (arrayMap[x - 1][y + 1] == 'X' && arrayMap[x + 1][y + 1] == 'X') {
                    direction = "R D U";
                }

            }

            //13
            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'R' && arrayMap[x + 1][y] == 'R' && arrayMap[x][y - 1] == 'R') {

                String removeBlank = " ";

                ArrayList<String> list = new ArrayList<String>();

                list.add("R");
                list.add("RD");
                list.add("D");
                list.add("LD");
                list.add("L");
                list.add("LU");
                list.add("U");
                list.add("RU");

                if (arrayMap[x - 1][y - 1] == 'X') {
                    list.remove("LU");

                }

                if (arrayMap[x - 1][y + 1] == 'X') {
                    list.remove("RU");
                }

                if (arrayMap[x + 1][y + 1] == 'X') {
                    list.remove("RD");
                }

                if (arrayMap[x + 1][y - 1] == 'X') {
                    list.remove("LD");
                }

                for (int i = 0; i < list.size(); i++) {
                    direction = direction + " " + list.get(i).toString();
                }

                removeBlank = direction.trim();
                direction = removeBlank.trim();

            }

        }

        //when the position is on the right line
        if (x != mapNumber - 1 && x != 0 && y == mapNumber - 1) {

            //1
            if (arrayMap[x][y - 1] == 'X') {
                if (arrayMap[x - 1][y] == 'R' && arrayMap[x + 1][y] == 'R') {
                    direction = "D U";
                }

                if (arrayMap[x - 1][y] == 'R' && arrayMap[x + 1][y] == 'X') {
                    direction = "U";
                }

                if (arrayMap[x - 1][y] == 'X' && arrayMap[x + 1][y] == 'R') {
                    direction = "D";
                }

                if (arrayMap[x - 1][y] == 'X' && arrayMap[x + 1][y] == 'X') {
                    direction = null;
                }

            }
            //2     
            if (arrayMap[x][y - 1] == 'R') {
                if (arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y] == 'R') {
                    direction = "D LD L";
                }

                if (arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y] == 'X') {
                    direction = "L";
                }

                if (arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y] == 'R') {
                    direction = "D L";
                }

                if (arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y] == 'X') {
                    direction = "L";
                }

            }

            //3
            if (arrayMap[x][y - 1] == 'R' && arrayMap[x - 1][y] == 'R') {

                if (arrayMap[x - 1][y] == 'X' && arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y] == 'R') {
                    direction = "D LD L";
                }

                if (arrayMap[x - 1][y] == 'X' && arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y] == 'X') {
                    direction = "L U";
                }

                if (arrayMap[x - 1][y] == 'X' && arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y] == 'R') {
                    direction = "D L U";
                }

                if (arrayMap[x - 1][y] == 'X' && arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y] == 'X') {
                    direction = "L U";
                }

                if (arrayMap[x - 1][y] == 'R' && arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y] == 'R') {
                    direction = "D LD L LU U";
                }

                if (arrayMap[x - 1][y] == 'R' && arrayMap[x + 1][y - 1] == 'R' && arrayMap[x + 1][y] == 'X') {
                    direction = "L LU U";
                }

                if (arrayMap[x - 1][y] == 'R' && arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y] == 'R') {
                    direction = "D L LU U";
                }

                if (arrayMap[x - 1][y] == 'R' && arrayMap[x + 1][y - 1] == 'X' && arrayMap[x + 1][y] == 'X') {
                    direction = "L LU U";
                }

            }

        }

//when the bottom is on the left bottom corner
        if (x == mapNumber - 1 && y == 0) {

            if (arrayMap[x - 1][y] == 'X') {
                if (arrayMap[x][y + 1] == 'R') {
                    direction = "U";
                }

                if (arrayMap[x][y + 1] == 'X') {
                    direction = null;
                }
            }

            if (arrayMap[x - 1][y] == 'R') {
                if (arrayMap[x - 1][y + 1] == 'R' && arrayMap[x][y + 1] == 'R') {
                    direction = "R U RU";
                }

                if (arrayMap[x - 1][y + 1] == 'R' && arrayMap[x][y + 1] == 'X') {
                    direction = "U RU";
                }

                if (arrayMap[x - 1][y + 1] == 'X' && arrayMap[x][y + 1] == 'R') {
                    direction = "R U";
                }

                if (arrayMap[x - 1][y + 1] == 'X' && arrayMap[x][y + 1] == 'X') {
                    direction = "U";
                }
            }
        }

        //当坐标在下边的边线上
        if (x == mapNumber - 1 && y != 0 && y != mapNumber - 1) {
            //1
            if (arrayMap[x - 1][y] == 'X') {
                if (arrayMap[x][y - 1] == 'R' && arrayMap[x][y + 1] == 'R') {
                    direction = "R L";
                }

                if (arrayMap[x][y - 1] == 'R' && arrayMap[x][y + 1] == 'X') {
                    direction = "L";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x][y + 1] == 'R') {
                    direction = "R";
                }

                if (arrayMap[x][y - 1] == 'X' && arrayMap[x][y + 1] == 'X') {
                    direction = null;
                }
            }

            //2
            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'X') {
                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x][y - 1] == 'R') {
                    direction = "L LU U";
                }

                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x][y - 1] == 'X') {
                    direction = "U";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x][y - 1] == 'R') {
                    direction = "L U";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x][y - 1] == 'X') {
                    direction = "U";
                }
            }

            //3
            if (arrayMap[x - 1][y] == 'R' && arrayMap[x][y + 1] == 'R') {
                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x - 1][y + 1] == 'R' && arrayMap[x][y - 1] == 'R') {
                    direction = "R L LU U RU";
                }

                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x - 1][y + 1] == 'X' && arrayMap[x][y - 1] == 'R') {
                    direction = "R L LU U";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x - 1][y + 1] == 'R' && arrayMap[x][y - 1] == 'R') {
                    direction = "R L U RU";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x - 1][y + 1] == 'X' && arrayMap[x][y - 1] == 'R') {
                    direction = "R L U";
                }

                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x - 1][y + 1] == 'R' && arrayMap[x][y - 1] == 'X') {
                    direction = "R U RU";
                }

                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x - 1][y + 1] == 'X' && arrayMap[x][y - 1] == 'X') {
                    direction = "R U";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x - 1][y + 1] == 'R' && arrayMap[x][y - 1] == 'X') {
                    direction = "R U RU";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x - 1][y + 1] == 'X' && arrayMap[x][y - 1] == 'X') {
                    direction = "R U";
                }
            }
        }

        //when the position is on the right corner
        if (x == mapNumber - 1 && y == mapNumber - 1) {
            //1
            if (arrayMap[x - 1][y] == 'X') {
                if (arrayMap[x][y - 1] == 'R') {
                    direction = "L";
                }

                if (arrayMap[x][y - 1] == 'X') {
                    direction = null;
                }
            }

            //2
            if (arrayMap[x - 1][y] == 'R') {
                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x][y - 1] == 'R') {
                    direction = "L LU U";
                }

                if (arrayMap[x - 1][y - 1] == 'R' && arrayMap[x][y - 1] == 'X') {
                    direction = "U";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x][y - 1] == 'R') {
                    direction = "L U";
                }

                if (arrayMap[x - 1][y - 1] == 'X' && arrayMap[x][y - 1] == 'X') {
                    direction = "U";
                }
            }

        }

      

        return direction;

    }

    /*
     *** how to move for ROBBIE
     */
    public String move(int x, int y, String direction) {

        String coordinate = " ";

        String newX = " ";
        String newY = " ";

        if ("RU".equals(direction) || "U".equals(direction) || "LU".equals(direction)) {
            x = x - 1;
        }

        if ("L".equals(direction) || "R".equals(direction)) {
            x = x;
        }

        if ("LD".equals(direction) || "D".equals(direction) || "RD".equals(direction)) {
            x = x + 1;
        }

        if ("LU".equals(direction) || "L".equals(direction) || "LD".equals(direction)) {
            y = y - 1;

        }

        if ("U".equals(direction) || "D".equals(direction)) {
            y = y;
        }

        if ("RU".equals(direction) || "R".equals(direction) || "RD".equals(direction)) {
            y = y + 1;

        }

        newX = String.valueOf(x);
        newY = String.valueOf(y);

        coordinate = newX + "," + newY;
        return coordinate;

    }

    public String returnDirection(int xDifference, int yDifference) {
        String back = " ";

        if (xDifference == 1) {
            if (yDifference == 1) {
                back = "LU";
            }
            if (yDifference == 0) {
                back = "U";
            }
            if (yDifference == -1) {
                back = "RU";
            }

        }

        if (xDifference == 0) {
            if (yDifference == 1) {
                back = "L";
            }

            if (yDifference == -1) {
                back = "R";
            }

        }

        if (xDifference == -1) {
            if (yDifference == 1) {
                back = "LD";
            }
            if (yDifference == 0) {
                back = "D";
            }
            if (yDifference == -1) {
                back = "RD";
            }

        }

        return back;
    }

       //calucate the rules of cost for ROBBIE
    public int cost(String direction) {
        int cost = 0;

        if ("RD".equals(direction) || "LD".equals(direction) || "LU".equals(direction) || "RU".equals(direction)) {
            cost = 1;
        }
        if ("R".equals(direction) || "D".equals(direction) || "L".equals(direction) || "U".equals(direction)) {
            cost = 2;

        }

        return cost;
    }

}
