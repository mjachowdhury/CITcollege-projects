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
public class TicTacToe extends JPanel  implements ActionListener {

    Player player1;
    Player player2;
    Player currentPlayer;
    JTextField player1ScoreFld;
    JTextField player2ScoreFld;
    TicTacToeCell[][] ticTacToeCells = new TicTacToeCell[3][3];
    JLabel messageLabel;
    boolean forceRestartGame= false;

    public TicTacToe() {
        super();
        player1 = new Player( "Player1",  "X", Color.BLUE );
        player2 = new Player( "Player2", "O", Color.MAGENTA );
        currentPlayer = player1;
        initializeGUI();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void initializeGUI() {
        this.setLayout( new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);

        addComponent( this, c, createScoreBoardUI(), 0,1,3, 1 );
        addComponent( this, c, createMessageUI(), 0,2,3, 1 );
        addComponent( this, c, createBoardUI(), 0,3,3, 1 );
        addComponent( this, c, createButtonsPanel(), 0, 4, 3, 1 );
    }

    private JPanel createScoreBoardUI() {
        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        JLabel label = new JLabel( "Player 1 - " + player1.getSymbol() );
        c.anchor = GridBagConstraints.WEST;
        addComponent( panel, c, label, 0,1,1, 1 );
        JPanel fillerPanel = new JPanel();
        fillerPanel.setPreferredSize( new Dimension(140, 10 ) );
        addComponent( panel, c, fillerPanel, 1,1,1, 1 );
        label = new JLabel( "Player 2 - " + player2.getSymbol() );
        c.anchor = GridBagConstraints.EAST;
        addComponent( panel, c, label, 2,1,1, 1 );
        Font bigFont = label.getFont().deriveFont( Font.BOLD, 30f );
        player1ScoreFld = new JTextField( "0", 3 );
        player1ScoreFld.setHorizontalAlignment( JTextField.RIGHT );
        player1ScoreFld.setFont( bigFont);
        addComponent( panel, c, player1ScoreFld, 0,2,1, 1 );
        player2ScoreFld = new JTextField( "0", 3 );
        player2ScoreFld.setHorizontalAlignment( JTextField.RIGHT );
        player2ScoreFld.setFont( bigFont);
        addComponent( panel, c, player2ScoreFld, 2,2,1, 1 );
        return panel;
    }

    private JPanel createMessageUI() {
        JPanel panel = new JPanel();
        messageLabel = new JLabel( "Player1 is playing" );
        messageLabel.setPreferredSize( new Dimension( 310, 30 ));
        messageLabel.setBorder( BorderFactory.createLineBorder( Color.BLACK ));
        messageLabel.setVerticalAlignment( JLabel.CENTER );
        messageLabel.setHorizontalAlignment( JLabel.CENTER );
        panel.add( messageLabel );
        return panel;
    }

    private JPanel createBoardUI() {
        JPanel panel = new JPanel();
        panel.setLayout( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2,2,2,2);
        TicTacToeCell ticTacToeCell;
        for( int row = 0; row < 3; row++ ) {
            for( int col = 0; col < 3; col++ ) {
                ticTacToeCell = new TicTacToeCell();
                ticTacToeCells[row][col] = ticTacToeCell;
                ticTacToeCell.setBorder( BorderFactory.createLineBorder( Color.BLACK ));
                ticTacToeCell.setTictactoe( this );
                ticTacToeCell.setActionCommand( "tictactoecell" );
                addComponent( panel, c, ticTacToeCell, col,row,1, 1 );
            }
        }
        panel.setBorder( BorderFactory.createLineBorder( Color.BLACK ));
        return panel;
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        JButton button = new JButton( "Restart Game" );
        button.setActionCommand( "restartgame" );
        button.addActionListener( this );
        panel.add( button );
        JPanel fillerPanel = new JPanel();
        fillerPanel.setPreferredSize( new Dimension(90, 10 ) );
        panel.add( fillerPanel );
        button = new JButton( "Reset Game" );
        button.setActionCommand( "resetgame" );
        button.addActionListener( this );
        panel.add( button );
        return panel;
    }

    private void addComponent( JPanel content, GridBagConstraints c, Component comp,
                               int column, int row, int gridWidth, int gridHeight ) {
        c.gridx = column;
        c.gridy = row;
        c.gridwidth = gridWidth;
        c.gridheight = gridHeight;
        content.add( comp, c );
    }

    public void switchPlayer() {
        if( currentPlayer == player1 ) {
            currentPlayer = player2;
        }
        else {
            currentPlayer = player1;
        }
        setMessage( currentPlayer.getPlayerName() + " is playing");
    }

    public void setMessage( String message ) {
        messageLabel.setText( message );
    }

    public boolean isForceRestartGame() {
        return forceRestartGame;
    }

    public void setForceRestartGame(boolean forceRestartGame) {
        this.forceRestartGame = forceRestartGame;
    }

    public boolean checkForWin() {
        boolean winFlag = false;
        if( checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin() ) {
            winFlag = true;
        }
        return winFlag;
    }

    public boolean checkRowsForWin() {
        boolean winFlag = false;
        String currentPlayerSymbol = currentPlayer.getSymbol();
        for( int row = 0; row < 3; row++ ) {
            if( ( ticTacToeCells[row][0].getText()).equalsIgnoreCase( currentPlayerSymbol )
                    && ( ticTacToeCells[row][1].getText() ).equalsIgnoreCase( currentPlayerSymbol )
                    && ( ticTacToeCells[row][2].getText() ).equalsIgnoreCase( currentPlayerSymbol) ) {
                winFlag = true;
                break;
            }
        }
        return winFlag;
    }

    public boolean checkColumnsForWin() {
        boolean winFlag = false;
        String currentPlayerSymbol = currentPlayer.getSymbol();
        for( int col = 0; col < 3; col++ ) {
            if( ( ticTacToeCells[0][col].getText()).equalsIgnoreCase( currentPlayerSymbol )
                    && ( ticTacToeCells[1][col].getText()).equalsIgnoreCase( currentPlayerSymbol )
                    && ( ticTacToeCells[2][col].getText()).equalsIgnoreCase( currentPlayerSymbol) ) {
                winFlag = true;
                break;
            }
        }
        return winFlag;
    }

    public boolean checkDiagonalsForWin() {
        boolean winFlag = false;
        String currentPlayerSymbol = currentPlayer.getSymbol();
        if( ( ticTacToeCells[0][0].getText()).equalsIgnoreCase( currentPlayerSymbol )
                && ( ticTacToeCells[1][1].getText()).equalsIgnoreCase( currentPlayerSymbol )
                && ( ticTacToeCells[2][2].getText() ).equalsIgnoreCase( currentPlayerSymbol ) ) {
            winFlag = true;
        }
        else if ( (ticTacToeCells[0][2].getText()).equalsIgnoreCase( currentPlayerSymbol )
                && ( ticTacToeCells[1][1].getText()).equalsIgnoreCase( currentPlayerSymbol )
                && ( ticTacToeCells[2][0].getText()).equalsIgnoreCase( currentPlayerSymbol ) ) {
            winFlag = true;
        }
        return winFlag;
    }

    public boolean isBoardFull() {
        boolean fullFlag = true;
        for( int row = 0; row < 3; row++ ) {
            for( int col = 0; col < 3; col++ ) {
                if( (ticTacToeCells[row][col].getText()).isEmpty() ) {
                    fullFlag = false;
                    break;
                }
            }
        }
        return fullFlag;
    }

    public void actionPerformed(ActionEvent evt) {
        String actCommand = evt.getActionCommand();
        if (actCommand.equalsIgnoreCase("restartgame")) {
            //System.out.println( "Restart game button clicked");
            setForceRestartGame( false );
            initializeCells();
        }
        else if (actCommand.equalsIgnoreCase("resetgame")) {
            //System.out.println( "Reset game button clicked");
            player1.setScore( 0 );
            player2.setScore( 0 );
            setForceRestartGame( false );
            updateScoreBoard();
            initializeCells();
        }
    }

    private void initializeCells() {
        TicTacToeCell ticTacToeCell;
        for( int row = 0; row < 3; row++ ) {
            for( int col = 0; col < 3; col++ ) {
                ticTacToeCell = ticTacToeCells[row][col];
                ticTacToeCell.setText( "" );
            }
        }
    }

    public void updateScoreBoard() {
        player1ScoreFld.setText( String.valueOf( player1.getScore() ) );
        player2ScoreFld.setText( String.valueOf( player2.getScore() ) );
    }

}
