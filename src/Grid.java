
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

public class Grid extends JPanel{
    private int l;
    private int h;
    private Case[][] cases;
    private boolean oldCases[][];

    public Grid(int l, int h) {
        this.l = l;
        this.h = h;
        cases = new Case[l][h];
        oldCases = new boolean[l][h];
        setLayout(new GridLayout(h,l));
        setPreferredSize(new Dimension(Case.getS()*l, Case.getS()*h));
        setFocusable(false) ;
        for (int i = 0; i < l; i++) {
            for( int j = 0; j < h; j++) {
                cases[i][j]= new Case();
                add(cases[i][j]);
            }
        }
    }

    public Boolean getOldCase(boolean  oldc[][], int i, int j) {
//        if (i >=  0 && j>= 0 && i < l && j < h) {
//            return oldc[i][j];
//        }else {
//            return false;
//        }
        // avec bord
        if (i <  0) {
            i = l-1;
        }else if( i >= l){
            i= 0;
        }
        if (j < 0 ){
            j = h-1;
        }else if(j >= h){
            j = 0;
        }
        return oldc[i][j];

    }

    public Boolean[] getOldAdj(boolean oldc[][],int i,int j) {
        Boolean r[] = new Boolean[8];
        int n =0;
        for(int k = -1; k <= 1; k++  ) {
            for (int m = -1; m <= 1; m++) {
                if(m != 0 || k != 0) {
                    r[n] = getOldCase(oldc, i+k,j+m);
                    n++;
                }
            }
        }
        return r;
    }

    public int nbOccupiedAdj(boolean  oldc[][], int i, int j) {
        Boolean adj[] = getOldAdj(oldc, i,j);
        int nbo = 0;
        for (int k = 0; k < 8; k++) {
            if(adj[k]) {
                nbo++;
            }
        }
        return nbo;
    }


    public void step() {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < h; j++) {
                oldCases[i][j] = cases[i][j].isOccupied();
            }
        }
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < h; j++) {
                int nbo = nbOccupiedAdj(oldCases, i, j);
                if(oldCases[i][j]) {
                    if(nbo <=1) {
                        cases[i][j].setOccupied(false);
                    }else if(nbo >= 4) {
                        cases[i][j].setOccupied(false);
                    }
                }else {
                    if(nbo == 3) {
                        cases[i][j].setOccupied(true);
                    }
                }
            }
        }
    }

}
