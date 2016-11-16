package com.melloware.jintellitype.test;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.IntellitypeListener;
import com.melloware.jintellitype.JIntellitype;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.border.EtchedBorder;

public class JIntellitypeTester extends JFrame
  implements HotkeyListener, IntellitypeListener
{
  private static JIntellitypeTester mainFrame;
  private static final int WINDOWS_A = 88;
  private static final int ALT_SHIFT_B = 89;
  private static final int CTRL_SHIFT_C = 90;
  private static final int PRINT_SCREEN = 91;
  private static final int F9 = 92;
  private static final int F12 = 93;
  private JButton btnRegisterHotKey = new JButton();
  private JButton btnUnregisterHotKey = new JButton();
  private JPanel bottomPanel = new JPanel();
  private JPanel mainPanel = new JPanel();
  private JPanel topPanel = new JPanel();
  private JScrollPane scrollPane = new JScrollPane();
  private JTextArea textArea = new JTextArea();

  public JIntellitypeTester()
  {
    initComponents();
  }

  public static void main(String[] args)
  {
    if (JIntellitype.checkInstanceAlreadyRunning("JIntellitype Test Application")) {
      System.exit(1);
    }

    if (!JIntellitype.isJIntellitypeSupported()) {
      System.exit(1);
    }

    mainFrame = new JIntellitypeTester();
    mainFrame.setTitle("JIntellitype Test Application");
    center(mainFrame);
    mainFrame.setVisible(true);
    mainFrame.initJIntellitype();
  }

  public void onHotKey(int aIdentifier)
  {
    output("WM_HOTKEY message received " + Integer.toString(aIdentifier));
  }

  public void onIntellitype(int aCommand)
  {
    switch (aCommand) {
    case 1:
      output("BROWSER_BACKWARD message received " + Integer.toString(aCommand));
      break;
    case 6:
      output("BROWSER_FAVOURITES message received " + Integer.toString(aCommand));
      break;
    case 2:
      output("BROWSER_FORWARD message received " + Integer.toString(aCommand));
      break;
    case 7:
      output("BROWSER_HOME message received " + Integer.toString(aCommand));
      break;
    case 3:
      output("BROWSER_REFRESH message received " + Integer.toString(aCommand));
      break;
    case 5:
      output("BROWSER_SEARCH message received " + Integer.toString(aCommand));
      break;
    case 4:
      output("BROWSER_STOP message received " + Integer.toString(aCommand));
      break;
    case 17:
      output("LAUNCH_APP1 message received " + Integer.toString(aCommand));
      break;
    case 18:
      output("LAUNCH_APP2 message received " + Integer.toString(aCommand));
      break;
    case 15:
      output("LAUNCH_MAIL message received " + Integer.toString(aCommand));
      break;
    case 11:
      output("MEDIA_NEXTTRACK message received " + Integer.toString(aCommand));
      break;
    case 14:
      output("MEDIA_PLAY_PAUSE message received " + Integer.toString(aCommand));
      break;
    case 12:
      output("MEDIA_PREVIOUSTRACK message received " + Integer.toString(aCommand));
      break;
    case 13:
      output("MEDIA_STOP message received " + Integer.toString(aCommand));
      break;
    case 9:
      output("VOLUME_DOWN message received " + Integer.toString(aCommand));
      break;
    case 10:
      output("VOLUME_UP message received " + Integer.toString(aCommand));
      break;
    case 8:
      output("VOLUME_MUTE message received " + Integer.toString(aCommand));
      break;
    case 16:
    default:
      output("Undefined INTELLITYPE message caught " + Integer.toString(aCommand));
    }
  }

  private static void center(JFrame aFrame)
  {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Point centerPoint = ge.getCenterPoint();
    Rectangle bounds = ge.getMaximumWindowBounds();
    int w = Math.min(aFrame.getWidth(), bounds.width);
    int h = Math.min(aFrame.getHeight(), bounds.height);
    int x = centerPoint.x - w / 2;
    int y = centerPoint.y - h / 2;
    aFrame.setBounds(x, y, w, h);
    if ((w == bounds.width) && (h == bounds.height)) {
      aFrame.setExtendedState(6);
    }
    aFrame.validate();
  }

  private void btnRegisterHotKey_actionPerformed(ActionEvent aEvent)
  {
    JIntellitype.getInstance().registerHotKey(88, 8, 65);
    JIntellitype.getInstance().registerHotKey(89, 5, 66);
    JIntellitype.getInstance().registerSwingHotKey(90, 3, 67);

    JIntellitype.getInstance().registerHotKey(91, 0, 44);
    JIntellitype.getInstance().registerHotKey(92, 0, 120);
    JIntellitype.getInstance().registerHotKey(93, 1, 123);

    this.textArea.setText("");
    output("RegisterHotKey WINDOWS+A was assigned uniqueID 88");
    output("RegisterHotKey ALT+SHIFT+B was assigned uniqueID 89");
    output("RegisterHotKey CTRL+SHIFT+C was assigned uniqueID 90");
    output("RegisterHotKey PRINT_SCREEN was assigned uniqueID 91");
    output("RegisterHotKey F9 was assigned uniqueID 92");
    output("RegisterHotKey F12 was assigned uniqueID 93");
    output("Press WINDOWS+A or ALT+SHIFT+B or CTRL+SHIFT+C in another application and you will see the debug output in the textarea.");
  }

  private void btnUnregisterHotKey_actionPerformed(ActionEvent aEvent)
  {
    JIntellitype.getInstance().unregisterHotKey(88);
    JIntellitype.getInstance().unregisterHotKey(89);
    JIntellitype.getInstance().unregisterHotKey(90);
    JIntellitype.getInstance().unregisterHotKey(91);
    JIntellitype.getInstance().unregisterHotKey(92);
    JIntellitype.getInstance().unregisterHotKey(93);
    output("UnregisterHotKey WINDOWS+A");
    output("UnregisterHotKey ALT+SHIFT+B");
    output("UnregisterHotKey CTRL+SHIFT+C");
    output("UnregisterHotKey PRINT_SCREEN");
    output("UnregisterHotKey F9");
    output("Press WINDOWS+A or ALT+SHIFT+B in another application and you will NOT see the debug output in the textarea.");
  }

  private void initComponents()
  {
    this.mainPanel.setLayout(new BorderLayout());
    this.topPanel.setBorder(new EtchedBorder(1));
    this.bottomPanel.setLayout(new BorderLayout());
    this.bottomPanel.setBorder(new EtchedBorder(1));
    this.btnRegisterHotKey.setText("RegisterHotKey");
    this.btnRegisterHotKey.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JIntellitypeTester.this.btnRegisterHotKey_actionPerformed(e);
      }
    });
    this.btnUnregisterHotKey.setText("UnregisterHotKey");
    this.btnUnregisterHotKey.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JIntellitypeTester.this.btnUnregisterHotKey_actionPerformed(e);
      }
    });
    this.topPanel.add(this.btnRegisterHotKey);
    this.topPanel.add(this.btnUnregisterHotKey);
    this.scrollPane.getViewport().add(this.textArea);
    this.bottomPanel.add(this.scrollPane, "Center");
    this.mainPanel.add(this.topPanel, "North");
    this.mainPanel.add(this.bottomPanel, "Center");

    addWindowListener(new WindowAdapter()
    {
      public void windowClosing(WindowEvent evt) {
        JIntellitype.getInstance().cleanUp();
        System.exit(0);
      }
    });
    getContentPane().add(this.mainPanel);
    pack();
    setSize(800, 600);
  }

  public void initJIntellitype()
  {
    try
    {
      JIntellitype.getInstance().addHotKeyListener(this);
      JIntellitype.getInstance().addIntellitypeListener(this);
      output("JIntellitype initialized");
    } catch (RuntimeException ex) {
      output("Either you are not on Windows, or there is a problem with the JIntellitype library!");
    }
  }

  private void output(String text)
  {
    this.textArea.append(text);
    this.textArea.append("\n");
  }
}