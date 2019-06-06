package com.taksila.javabyexample.projects.tictactoe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Copyright (c) 2016, Taksila LLC. All rights reserved.
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * <p>
 * - Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 * <p>
 * - Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * <p>
 * - Neither the name of Taksila LLC or the names of its
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * Created by Taksila LLC on 12/8/2016.
 */
public class TicTacToeCell extends JButton  implements ActionListener {

    private int PREF_W = 100;
    private int PREF_H = 100;

    private TicTacToe tictactoe = null;

    public TicTacToeCell() {
        super();
        Font bigFont = this.getFont().deriveFont( Font.BOLD, 80f );
        this.setFont( bigFont );
        this.setHorizontalAlignment( JButton.CENTER );
        this.setVerticalAlignment( JButton.CENTER );
        this.addActionListener( this );
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public void setSymbol() {
        String symbol = tictactoe.getCurrentPlayer().getSymbol();
        this.setText( symbol );
    }

    public void setTictactoe(TicTacToe tictactoe) {
        this.tictactoe = tictactoe;
    }

    public void actionPerformed(ActionEvent evt) {
        String actCommand = evt.getActionCommand();
        if (actCommand.equalsIgnoreCase("tictactoecell")) {
            TicTacToeCell cell = (TicTacToeCell)evt.getSource();
            if( tictactoe.isForceRestartGame() ) {
                tictactoe.setMessage("Restart the game to continue playing");
            }
            else {
                if( !cell.getText().isEmpty() ) {
                    tictactoe.setMessage(tictactoe.getCurrentPlayer().getPlayerName() + " : Please click on empty cell");
                    return;
                }
                cell.setSymbol();
                if (tictactoe.checkForWin()) {
                    tictactoe.getCurrentPlayer().incrementScore();
                    tictactoe.updateScoreBoard();
                    tictactoe.setMessage(tictactoe.getCurrentPlayer().getPlayerName() + " has won this game");
                    tictactoe.setForceRestartGame( true );
                } else if (tictactoe.isBoardFull()) {
                    tictactoe.setMessage("Draw - Both players played well!");
                    tictactoe.setForceRestartGame( true );
                } else {
                    tictactoe.switchPlayer();
                }
            }
        }

    }


}
