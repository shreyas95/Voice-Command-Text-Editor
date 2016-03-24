/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shreyas
 */

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.io.PrintStream;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.ItemSelectable;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.swing.*;

import java.awt.*;
import javax.swing.undo.*;

public class mytexteditor1 extends JFrame implements ActionListener,KeyListener,ItemListener
{
        Thread td;
	Container con;
	JButton bnew,bopen,bsave,bfind,bsetmargin,bbold,bitalic,bunderline;
	JTextPane a1;
	JMenuItem paste;
	JMenuItem fc;
	int itop=20,ibottom=100,iright=20,ileft=20;
	JTextField t1,ttop,tleft,tright,tbottom,twordcount,tlettercount,tlinecount,tsentencecount;;
	JLabel top,right,left,bottom,wordcount,lettercount,linecount,sentencecount;
	Insets m;
	JScrollPane scroll;
	JComboBox lang;
	String [] langs;
	JComboBox<Integer> fontsize;
	Integer [] fonts;
	String save="",open="",cp=null,ncp="",total="";
	String t="";
	int newoption=1,savefirst=0,ibold=0,iitalic=0,iunderline=0;
	int x= 0;
    int h = 0;
    String f="";
    String s="";
    JToolBar toolbar1,toolbar2,toolbar3;
    JCheckBoxMenuItem b1,b2,b3,b4,b5,b6,b7,b8,b9;
    JMenuItem New;
	JMenuItem mopen;
	JMenuItem msave;
    //=============================(find replace)=================================
    JTabbedPane tb;
	JTextField tfs,t2,t3;
	JPanel p1,p2;
	JButton findmyidea,replacemyidea;
	String b="",br="";
	int q=0,u=0,qr=0,ur=0;
	JCheckBox c1,c2;
    //============================================================================
	public mytexteditor1() 
	{
		con = new Container();
		this.getContentPane();
		this.setTitle("MyEditor "+"(Unsaved document)");
		//=====================================================================
		JMenuBar mb =new JMenuBar();
		setJMenuBar(mb);
		mb.setToolTipText("Menubar"); //                 I LIKE IT
		JMenu file=new JMenu("File  ");
		JMenu edit=new JMenu("Edit  ");
		JMenu view=new JMenu("View  ");
		JMenu insert=new JMenu("Insert  ");
		JMenu format=new JMenu("Format  ");
		JMenu table=new JMenu("Table  ");
		JMenu tools=new JMenu("Tools  ");
		JMenu window=new JMenu("Window  ");
		JMenu help=new JMenu("Help  ");
		mb.setFocusable(false);
		mb.add(file);
		mb.add(edit);
		mb.add(view);
		mb.add(insert);
		mb.add(format);
		mb.add(table);
		mb.add(tools);
		mb.add(window);
		mb.add(help);
		file.setToolTipText("You can perform operations like opening the file,closing the file,saving the file etc");
		//==========================(File)========================================================
		 New=new JMenuItem("New");
		 mopen=new JMenuItem("Open");
		 msave=new JMenuItem("Save");
		JMenuItem saveas=new JMenuItem("Save As");
		JMenuItem saveas_pdf=new JMenuItem("Save as PDF");
                JMenuItem saveas_docx=new JMenuItem("Save as docx");
		JMenuItem close=new JMenuItem("Close");
		file.add(New);
		file.add(mopen);
		file.add(msave);
		file.add(saveas);
		file.add(saveas_pdf);
                file.add(saveas_docx);
		file.add(close);
		New.addActionListener(this);
		mopen.addActionListener(this);
		msave.addActionListener(this);
		saveas.addActionListener(this);
		saveas_pdf.addActionListener(this);
                saveas_docx.addActionListener(this);
		close.addActionListener(this);
		//=============================(Edit)==============================================
		JMenuItem undo=new JMenuItem("Undo");
		JMenuItem cut=new JMenuItem("Cut");
		JMenuItem copy=new JMenuItem("Copy");
		paste=new JMenuItem("Paste");
		fc=new JMenuItem("Find/Replace");
		edit.add(undo);
		edit.add(cut);
		edit.add(copy);
		edit.add(paste);
		edit.add(fc);
		undo.addActionListener(this);
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		fc.addActionListener(this);
		//=============================(view)===============================================
		JMenu to1=new JMenu("Toolbar1");
		JMenu to2=new JMenu("Toolbar2");
		JMenu to3=new JMenu("Toolbar3");
		 b1=new JCheckBoxMenuItem("New");
		 b1.setSelected(true);
		 b2=new JCheckBoxMenuItem("Open");
		 b2.setSelected(true);
		 b3=new JCheckBoxMenuItem("Margin");
		 b3.setSelected(true);
		 b4=new JCheckBoxMenuItem("Find");
		 b4.setSelected(true);
		 b5=new JCheckBoxMenuItem("Bold/Italic");
		 b5.setSelected(true);
		 b6=new JCheckBoxMenuItem("Lettercount");
		 b6.setSelected(true);
		 b7=new JCheckBoxMenuItem("Wordcount");
		 b7.setSelected(true);
		 b8=new JCheckBoxMenuItem("Linecount");
		 b8.setSelected(true);
		 b9=new JCheckBoxMenuItem("Sentencecount");
		 b9.setSelected(true);
		 b1.addItemListener(this);
		 b2.addItemListener(this);
		 b3.addItemListener(this);
		 b4.addItemListener(this);
		 b5.addItemListener(this);
		 b6.addItemListener(this);
		 b7.addItemListener(this);
		 b8.addItemListener(this);
		 b9.addItemListener(this);
		view.add(to1);
		view.add(to2);
		view.add(to3);
		to1.add(b1);
		to1.add(b2);
		to1.add(b3);
		to2.add(b4);
		to2.add(b5);
		to3.add(b6);
		to3.add(b7);
		to3.add(b8);
		to3.add(b9);	
		//=============================(textarea)====================================================
		this.setLayout(null);
		a1=new JTextPane();
		a1.addKeyListener(this);
                    
		a1.setAlignmentX(ileft);
		scroll = new JScrollPane (a1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scroll);
		scroll.setBounds(178,80, 1000, 585);
		//==============================(toolbar)==================================================
		bnew = new JButton("    New    ");
		bopen = new JButton("    Open    ");
		bsave = new JButton("    Save    ");
		bnew.addActionListener(this);
		bopen.addActionListener(this);
		bsave.addActionListener(this);
		toolbar1=new JToolBar("Toolbar1");
		toolbar1.setFloatable(true);
		toolbar1.setRollover(true);
		toolbar1.add(bnew);
		toolbar1.addSeparator();
		toolbar1.add(bopen);
		toolbar1.addSeparator();
		toolbar1.add(bsave);
		ttop=new JTextField(0+"", 05);
		tleft=new JTextField(0+"", 05);
		tright=new JTextField(0+"", 05);
		tbottom=new JTextField(0+"", 05);
		ttop.addActionListener(this);
		top= new JLabel("Top");
		left= new JLabel("Left");
		right= new JLabel("Right");
		bottom= new JLabel("bottom");
		bsetmargin=new JButton("Set Margin");
		bsetmargin.addActionListener(this);
		toolbar1.addSeparator();
		toolbar1.addSeparator();
		toolbar1.addSeparator();
		toolbar1.add(bsetmargin);
		toolbar1.addSeparator();
		toolbar1.add(top);
		toolbar1.addSeparator();
		toolbar1.add(ttop);
		toolbar1.addSeparator();
		toolbar1.addSeparator();
		toolbar1.add(left);
		toolbar1.addSeparator();
		toolbar1.add(tleft);
		toolbar1.addSeparator();
		toolbar1.addSeparator();
		toolbar1.add(right);
		toolbar1.addSeparator();
		toolbar1.add(tright);
		toolbar1.addSeparator();
		toolbar1.addSeparator();
		toolbar1.add(bottom);
		toolbar1.addSeparator();
		toolbar1.add(tbottom);
		toolbar1.addSeparator();
		toolbar1.addSeparator();
		toolbar1.addSeparator();
		this.add(toolbar1);
		toolbar1.setBounds(0, 0, 1376, 23);
		//======================================(toolbar2)=================================
		 lang = new JComboBox();
		String langs[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for ( int i = 0; i < langs.length; i++ )
        {
          lang.addItem(langs[i]);
        }
		lang.addItemListener(this);
		fonts =new Integer[13];
		fonts[0]=4;fonts[1]=6;fonts[2]=10;fonts[3]=12;fonts[4]=14;fonts[5]=16;fonts[6]=20;fonts[7]=24;fonts[8]=30;fonts[9]=36;fonts[10]=44;fonts[11]=72;fonts[12]=98;
		fontsize = new JComboBox<>(fonts);
		fontsize.addActionListener(this);
		fontsize.setSelectedIndex(3);
		t1=new JTextField("Find the word", 05);
		bfind=new JButton("     Find     ");
		bfind.addActionListener(this);
		bfind.setFocusable(false);
		bbold=new JButton("  B  ");
		bbold.addActionListener(this);
		bitalic=new JButton("  I  ");
		bitalic.addActionListener(this);
		bunderline=new JButton("  U  ");
		bunderline.setToolTipText("It doesn't work!!!!! Don't cry");
		bunderline.addActionListener(this);
		toolbar2=new JToolBar("Toolbar2");
		toolbar2.setFloatable(true);
		toolbar2.setRollover(true);
		toolbar2.add(lang);
		toolbar2.addSeparator();
		toolbar2.addSeparator();
		toolbar2.addSeparator();
		toolbar2.add(fontsize);
		toolbar2.addSeparator();
		toolbar2.addSeparator();
		toolbar2.addSeparator();
		toolbar2.add(bfind);
		toolbar2.addSeparator();
		toolbar2.add(t1);
		toolbar2.addSeparator();	
		toolbar2.addSeparator();
		toolbar2.add(bbold);
		toolbar2.addSeparator();
		toolbar2.addSeparator();
		toolbar2.add(bitalic);
		toolbar2.addSeparator();	
		toolbar2.addSeparator();
		toolbar2.add(bunderline);
		//=========================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================
		toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();
		toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();
		toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	toolbar2.addSeparator();	
		//=========================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================================
		toolbar2.addSeparator();	
		toolbar2.addSeparator();	
		toolbar2.setFocusable(false);
		this.add(toolbar2);
		toolbar2.setBounds(0, 23, 1376, 23);
		//==================================(toolbar3)======================================
		toolbar3=new JToolBar("Toolbar2");
		toolbar3.setFloatable(true);
		toolbar3.setRollover(true);
		lettercount=new JLabel("Letters");
		wordcount = new JLabel("Words");
		linecount = new   JLabel("Lines");
		sentencecount = new JLabel("Sentences");
		tlettercount=new JTextField();
		twordcount = new JTextField();
		tlinecount = new   JTextField();
		tsentencecount = new JTextField();
		toolbar3.addSeparator();
		toolbar3.add(lettercount);
		toolbar3.addSeparator();
		toolbar3.add(tlettercount);
		toolbar3.addSeparator();
		toolbar3.addSeparator();
		toolbar3.add(wordcount);
		toolbar3.addSeparator();
		toolbar3.add(twordcount);
		toolbar3.addSeparator();
		toolbar3.addSeparator();
		toolbar3.add(linecount);
		toolbar3.addSeparator();
		toolbar3.add(tlinecount);
		toolbar3.addSeparator();
		toolbar3.addSeparator();
		toolbar3.add(sentencecount);
		toolbar3.addSeparator();
		toolbar3.add(tsentencecount);
		toolbar3.addSeparator();
		toolbar3.addSeparator();
		this.add(toolbar3);
		toolbar3.setBounds(0, 46, 1376, 23);
		//=====================================================================================
		//===================================================================================
		this.setSize(1376,740);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
                
                 td = new Thread(new Runnable() {

            @Override
            public void run() {
                 ConfigurationManager cm;
        
            cm = new ConfigurationManager(mytexteditor1.class.getResource("helloworld.config.xml"));
        Recognizer recognizer = (Recognizer)cm.lookup("recognizer");
        recognizer.allocate();
        Microphone microphone = (Microphone)cm.lookup("microphone");
        if(!microphone.startRecording())
        {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }
        System.out.println("Say: (Good morning | Hello) ( world | open | two )");
        do
        {
            System.out.println("Start speaking. Press Ctrl-C to quit.\n");
            Result result = recognizer.recognize();
            if(result != null)
            {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println((new StringBuilder()).append("You said: ").append(resultText).append('\n').toString());
                String temp = a1.getText();
                temp = temp + resultText;
                a1.setText(temp);
            } else
            {
                System.out.println("I can't hear what you said.\n");
            }
        } while(true);
            }
        });
        td.start();
	}
	public static void main(String[] args) 
	{
		new mytexteditor1();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
//----------------BOLD - ITALIC - UNDERLINE START-------------------------------------------------------------------------------------------------
            if(e.getSource()==bbold)
            {
                
            }
            if(e.getSource()==bitalic)
            {

            }
            if(e.getSource()==bunderline)
            {

            }   
//----------------------BOLD - ITALIC - UNDERLINE END-------------------------------------------------------------------------------------------------------------------
//----------------------MARGIN START-------------------------------------------------------------------------------------------------------                
        if(e.getSource()==bsetmargin)
        {
                itop=Integer.parseInt(ttop.getText());
                ileft=Integer.parseInt(tleft.getText());
                iright=Integer.parseInt(tright.getText());
                ibottom=Integer.parseInt(tbottom.getText());
                m=new Insets(itop, ileft, ibottom, iright);
                a1.selectAll();
                a1.cut();
                a1.setMargin(m);
                a1.paste();
                System.out.println("margin");
        }
//--------------------------MARGIN END----------------------------------------------------------------------------------------------------                
        if(e.getSource()==fontsize)
        {
                int x=(Integer)fontsize.getSelectedItem();
                a1.setFont(new Font(Font.DIALOG,Font.PLAIN,x));
        }
        if(e.getSource()==lang)
        {
                System.out.println("pansare");
        }
//---------------------------FIND START------------------------------------------------------------------------------------- 
        if(e.getSource()==bfind)
        {
                bfind.setFocusable(false);
                System.out.println("findmyidea");
                b=a1.getText();
                System.out.println(b);
                String f=t1.getText();
                q=b.indexOf(f, u);
                System.out.println(q);
                if(q!=-1)
                {
                        a1.setFocusable(true);
                        a1.select(q, q+f.length());
                        u=q+1;
                }
                else
                {
                        JOptionPane.showMessageDialog(this,"No (more) matches");
                        u=q=0;
                }
        }
//---------------------------------FIND END-----------------------------------------------------------------
// ====================================(file start)==========================================================
        if(e.getSource()==New || e.getSource()==bnew)
        {
//			savefirst=0;
//			if(newoption==1)
//			{
//			System.out.println("new");
//                       
//			new mytexteditor1();//           love you obsssssssss!!!!!!
//			}
//			else
//			{
//				a1.setVisible(true);
//				newoption=1;
//			}
            FileMenu filemenu = new FileMenu();
            filemenu.submenu_new(a1);
        }
        if(e.getSource()==mopen || e.getSource()==bopen)
        {
//			System.out.println("open");
//			final JFrame frame=new JFrame("JFile chooser");
//			Container contentPane=frame.getContentPane();
//			
//			final JLabel directoryLabel=new JLabel(" ");
//			directoryLabel.setFont(new Font("Serif", Font.BOLD,36));
//			contentPane.add(directoryLabel,BorderLayout.NORTH);
//			
//			final JLabel filenameLabel = new JLabel(" ");
//			filenameLabel.setFont(new Font("Serif", Font.BOLD,36));
//			contentPane.add(filenameLabel,BorderLayout.SOUTH);
//			
//			JFileChooser filechooser=new JFileChooser(".");
//			filechooser.setControlButtonsAreShown(true);//       control buttons
//			contentPane.add(filechooser,BorderLayout.CENTER);
//			
//			ActionListener actionlistener=new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) 
//				{
//					// TODO Auto-generated method stub
//					JFileChooser thefilechooser=(JFileChooser)e.getSource();
//					if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
//					{
//						File selectedfile = thefilechooser.getSelectedFile();
//						directoryLabel.setText(selectedfile.getParent());
//						filenameLabel.setText(selectedfile.getName());
//                                        
//						open=directoryLabel.getText() +"\\"+ filenameLabel.getText();
//						System.out.println("File which you"+open);
//						FileInputStream input1 = null;
//						try {
//							input1 = new FileInputStream(""+open);
//						} catch (FileNotFoundException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						int c=0;
//						String d="";
//						do
//						{
//							try {
//								c=(byte) input1.read();
//							} catch (IOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//							}
//							System.out.print((char)c);
//							d=d+(char)c;
//							a1.setText(d);
//						}while(c!=-1);
//						frame.setVisible(false);
//                                                setTitle("MyTextEditor "+"("+open+")");
//					}else if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
//					{
//						directoryLabel.setText(" ");
//						filenameLabel.setText(" ");
//                                                frame.setVisible(false);
//					}		
//				}
//				
//			};
//			
//			filechooser.addActionListener(actionlistener);
//			frame.pack();
//			frame.setVisible(true);

                //===============(file is choosen now open it)=================================
                //================(yedu var bagh open zali)====================================
            FileMenu filemenu = new FileMenu();
            filemenu.submenu_open(a1);
        }
        if(e.getSource()==msave || e.getSource()==bsave)
        {
//			System.out.println("save");
//			if(savefirst==0)
//			{
//			final JFrame frame=new JFrame("Saving the file");
//			Container contentPane=frame.getContentPane();
//			
//			final JLabel directoryLabel=new JLabel(" ");
//			directoryLabel.setFont(new Font("Serif", Font.BOLD,36));
//			contentPane.add(directoryLabel,BorderLayout.NORTH);
//			
//			final JLabel filenameLabel = new JLabel(" ");
//			filenameLabel.setFont(new Font("Serif", Font.BOLD,36));
//			contentPane.add(filenameLabel,BorderLayout.SOUTH);
//			
//			JFileChooser filechooser=new JFileChooser("");
//			filechooser.setApproveButtonText("Save");
//			contentPane.add(filechooser,BorderLayout.CENTER);
//			ActionListener actionlistener=new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) 
//				{
//					
//					// TODO Auto-generated method stub
//					JFileChooser thefilechooser=(JFileChooser)e.getSource();
//					if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
//					{
//						File selectedfile = thefilechooser.getSelectedFile();
//						directoryLabel.setText(selectedfile.getParent());
//						filenameLabel.setText(selectedfile.getName());
//						save=directoryLabel.getText() + "\\" + filenameLabel.getText();
//						System.out.println("Path selected for saving is :- "+save);
//						String d="";
//						d=a1.getText();
//						byte b []=d.getBytes();
//						System.out.println(d);
//						File f=new File(save);
//						try {
//							FileOutputStream fout=new FileOutputStream(f);
//							fout.write(b);
//							fout.close();
//							savefirst=1;
//						} catch (Exception e1) 
//						{
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						frame.setVisible(false);
//                                                setTitle("MyTextEditor "+"("+save+")");
//					}else if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
//					{
//						directoryLabel.setText(" ");
//						filenameLabel.setText(" ");
//                                                frame.setVisible(false);
//					}
//					
//				}
//			};
//			filechooser.addActionListener(actionlistener);
//			frame.pack();
//			frame.setVisible(true);
//                        }
//			else if(savefirst==1)
//			{
//				System.out.println("Path selected for saving is :- "+save);
//				String d="";
//				d=a1.getText();
//				byte b []=d.getBytes();
//				System.out.println(d);
//				File f=new File(save);
//				try {
//					FileOutputStream fout=new FileOutputStream(f);
//					fout.write(b);
//					fout.close();
//				} catch (Exception e1) 
//				{
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				//this.setTitle("MyTextEditor "+"("+save+")");
//			}
            FileMenu filemenu = new FileMenu();
            filemenu.submenu_save(a1);
        }
        if(e.getActionCommand().equals("Save As"))
        {
//			System.out.println("saveas");
//			final JFrame frame=new JFrame("Saving the file");
//			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//			Container contentPane=frame.getContentPane();
//			
//			final JLabel directoryLabel=new JLabel(" ");
//			directoryLabel.setFont(new Font("Serif", Font.BOLD,36));
//			contentPane.add(directoryLabel,BorderLayout.NORTH);
//			
//			final JLabel filenameLabel = new JLabel(" ");
//			filenameLabel.setFont(new Font("Serif", Font.BOLD,36));
//			contentPane.add(filenameLabel,BorderLayout.SOUTH);
//			
//			JFileChooser filechooser=new JFileChooser("");
//			filechooser.setApproveButtonText("Save");
//			contentPane.add(filechooser,BorderLayout.CENTER);
//			ActionListener actionlistener=new ActionListener() {
//				
//				@Override
//				public void actionPerformed(ActionEvent e) 
//				{
//					// TODO Auto-generated method stub
//					JFileChooser thefilechooser=(JFileChooser)e.getSource();
//					if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
//					{
//						File selectedfile = thefilechooser.getSelectedFile();
//						directoryLabel.setText(selectedfile.getParent());
//						filenameLabel.setText(selectedfile.getName());
//						save=directoryLabel.getText() + "\\" + filenameLabel.getText();
//						System.out.println("Path selected for saving is :- "+save);
//						String d="";
//						d=a1.getText();
//						byte b []=d.getBytes();
//						System.out.println(d);
//						File f=new File(save);
//						try {
//							FileOutputStream fout=new FileOutputStream(f);
//							fout.write(b);
//							fout.close();
//							savefirst=1;
//						} catch (Exception e1) 
//						{
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//						frame.setVisible(false);
//                                                setTitle("MyTextEditor "+"("+save+")");
//					}else if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
//					{
//						directoryLabel.setText(" ");
//						filenameLabel.setText(" ");
//                                                frame.setVisible(false);
//					}
//					
//				}
//			};
//			
//			filechooser.addActionListener(actionlistener);
//			frame.pack();
//			frame.setVisible(true);
            FileMenu filemenu = new FileMenu();
            filemenu.submenu_saveas(a1);
        }
        if(e.getActionCommand().equals("Save as PDF"))
        {
                System.out.println("save as pdf");
        //        Pdfsave.saveaspdf();                    //check it out
        }
        if(e.getActionCommand().equals("Save as docx"))
        {
                System.out.println("save as docx");
        }
        if(e.getActionCommand().equals("Close"))
        {
                System.out.println("close");
                a1.setVisible(false);
                newoption=0;
        }
        //========================(file end)===================================
        //========================(edit start)=================================
        if(e.getActionCommand().equals("Undo"))
        {
                System.out.println("Undo");
        }
        if(e.getActionCommand().equals("Cut"))
        {
                String m=null;
                System.out.println("Cut");
                cp=a1.getSelectedText();
                total=a1.getText();
                m=total.replaceAll(cp,"");
                System.out.println(m);
                a1.setText(m);
        }
        if(e.getActionCommand().equals("Copy"))
        {
                System.out.println("Copy");
                cp=a1.getSelectedText();
        }
        if(e.getActionCommand().equals("Paste"))
        {
                if(cp==null)
                {
                        paste.setEnabled(false);
                }
                else
                {
                        paste.setEnabled(true);
                        int c;
                        String m,n;
                        System.out.println("Paste");
                        c=a1.getCaretPosition();
                        System.out.println(c);
                        total=a1.getText();
                        m=total.substring(0, c);
                        n=total.substring(c);
                        a1.setText(m+cp+n);
                        System.out.println(m+cp+n);
                }
        }
        if(e.getActionCommand().equals("Find/Replace"))
        {
                System.out.println("Find");
                //new Findreplace(a1);
                JFrame frame= new JFrame("Find/Replace");
                tb = new JTabbedPane();
                p1=new JPanel(new FlowLayout(FlowLayout.LEFT));
                p2=new JPanel(new FlowLayout(FlowLayout.LEFT));
                tfs=new JTextField("hello",20);
                t2=new JTextField(20);
                t3=new JTextField(20);
                findmyidea=new JButton("find next");
                replacemyidea=new JButton("Replace");
                c1=new JCheckBox("Check from start", false);
                c2=new JCheckBox("Check from start", false);
                p1.add(tfs);
                p1.add(findmyidea);
                p1.add(c1);
                findmyidea.setFocusable(false);
                replacemyidea.setFocusable(false);
                p2.add(t2);
                p2.add(t3);
                p2.add(replacemyidea);
                p2.add(c2);
                tb.add("Find",p1);
                tb.add("Replace",p2);
                frame.add(tb);
                findmyidea.addActionListener(this);
                replacemyidea.addActionListener(this);
                frame.setSize(400, 175);
                //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                frame.setVisible(true);
        }
        if(e.getSource()==findmyidea)
        {
                if(c1.isSelected()==true)
                {
                        System.out.println("reset");
                        u=q=0;
                }
                System.out.println("findmyidea");
                b=a1.getText();
                System.out.println(b);
                String f=tfs.getText();
                q=b.indexOf(f, u);
                System.out.println(q);
                if(q!=-1)
                {
                        a1.setFocusable(true);
                        a1.select(q, q+f.length());
                        u=q+1;
                }
                else
                {
                        JOptionPane.showMessageDialog(this,"No (more) matches");
                }
        }
        if(e.getSource()==replacemyidea)
        {
                if(c2.isSelected()==true)
                {
                        System.out.println("reset");
                        ur=qr=0;
                }
                System.out.println("replace");
                br=a1.getText();
                String fr=t2.getText();
                qr=br.indexOf(fr, ur);
                System.out.println(qr);
                if(qr!=-1)
                {
                        String fir=br.substring(0, qr);
                        fir=fir+t3.getText()+br.substring((qr+fr.length()), br.length());
                        a1.setText(fir);
                        a1.setFocusable(true);
                        a1.select(qr, qr+t3.getText().length());
                        ur=qr+1;
                }
                else
                {
                        JOptionPane.showMessageDialog(this,"No (more) matches");
                }
        }
}
@Override
public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==a1)
        {

        }
}
@Override
public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        int i=0,letters=-1,words=0,sent=0,lines=0;
        //System.out.println("shreyas");
        String str="a"+a1.getText();
        System.out.println(str);

        //==================================(letters)================================
        for(i=0;i<str.length();i++)
        {
                if((str.charAt(i) != '\r') && (str.charAt(i) != '\n')&& (str.charAt(i) != ' '))
                {
                        letters++;
                }
        }
        tlettercount.setText(letters+"");
        //===================================(words)=================================
        for(i=0;i<str.length();i++)
        {
                if(str.charAt(i)==' ' && (str.charAt(i-1)!=' ' && str.charAt(i-1)!='\n') )
                {
                        words++;
                }
        }
        twordcount.setText(words+"");
        //===================================(Lines)==================================
        for(i=0;i<str.length();i++)
        {
                if(str.charAt(i)=='\n')
                {
                        lines++;
                }
        }
        tlinecount.setText(lines+"");
        //==================================(sentences)===============================
        for(i=0;i<str.length();i++)
        {
                if((str.charAt(i)=='.' && str.charAt(i-1)!='.') || (str.charAt(i)=='?' && str.charAt(i-1)!='?') || (str.charAt(i)=='!' && str.charAt(i-1)!='!'))
                {
                        sent++;
                }
        }
        tsentencecount.setText(sent+"");
        System.out.println("shreyas");

}
@Override
public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

}
@Override
public void itemStateChanged(ItemEvent e) {
        // TODO Auto-generated method stub
        try
{
   if (e.getStateChange() == ItemEvent.SELECTED)
   {
       a1.setFont(new Font(lang.getSelectedItem().toString(), a1.getFont().getStyle(), a1.getFont().getSize()));
   }
}
catch(NumberFormatException err){}
        //=====================================================================
        if(e.getSource()==b1)
        {
                if(b1.isSelected()==true)
                {
                        bnew.setVisible(true);
                }
                else
                {
                        bnew.setVisible(false);
                }
        }
        //============================
        if(e.getSource()==b2)
        {
                if(b2.isSelected()==true)
                {
                        bopen.setVisible(true);
                }
                else
                {
                        bopen.setVisible(false);
                }
        }
        //============================
        if(e.getSource()==b3)
        {
                if(b3.isSelected()==true)
                {
                        tleft.setVisible(true);
                        ttop.setVisible(true);
                        tright.setVisible(true);
                        tbottom.setVisible(true);
                        left.setVisible(true);
                        top.setVisible(true);
                        right.setVisible(true);
                        bottom.setVisible(true);
                        bsetmargin.setVisible(true);
                }
                else
                {
                        tleft.setVisible(false);
                        ttop.setVisible(false);
                        tright.setVisible(false);
                        tbottom.setVisible(false);
                        left.setVisible(false);
                        top.setVisible(false);
                        right.setVisible(false);
                        bottom.setVisible(false);
                        bsetmargin.setVisible(false);
                }
        }
                        //============================
                        if(e.getSource()==b4)
                        {
                                if(b4.isSelected()==true)
                                {
                                        bfind.setVisible(true);
                                        t1.setVisible(true);
                                }
                                else
                                {
                                        bfind.setVisible(false);
                                        t1.setVisible(false);
                                }
                        }
                        //============================
                        if(e.getSource()==b5)
                        {
                                if(b5.isSelected()==true)
                                {
                                        bbold.setVisible(true);
                                        bitalic.setVisible(true);
                                        bunderline.setVisible(true);
                                }
                                else
                                {
                                        bbold.setVisible(false);
                                        bitalic.setVisible(false);
                                        bunderline.setVisible(false);
                                }
                        }
                        //============================
                        if(e.getSource()==b6)
                        {
                                if(b6.isSelected()==true)
                                {
                                        tlettercount.setVisible(true);
                                        lettercount.setVisible(true);
                                }
                                else
                                {
                                        tlettercount.setVisible(false);
                                        lettercount.setVisible(false);
                                }
                        }
                        //============================
                        if(e.getSource()==b7)
                        {
                                if(b7.isSelected()==true)
                                {
                                        twordcount.setVisible(true);
                                        wordcount.setVisible(true);
                                }
                                else
                                {
                                        twordcount.setVisible(false);
                                        wordcount.setVisible(false);
                                }
                        }
                        //============================
                        if(e.getSource()==b8)
                        {
                                if(b8.isSelected()==true)
                                {
                                        tlinecount.setVisible(true);
                                        linecount.setVisible(true);
                                }
                                else
                                {
                                        tlinecount.setVisible(false);
                                        linecount.setVisible(false);
                                }
                        }
                        //============================
                        if(e.getSource()==b9)
                        {
                                if(b9.isSelected()==true)
                                {
                                        tsentencecount.setVisible(true);
                                        sentencecount.setVisible(true);
                                }
                                else
                                {
                                        tsentencecount.setVisible(false);
                                        sentencecount.setVisible(false);
                                }
                        }
                        if(e.getSource()==b9 || e.getSource()==b8 || e.getSource()==b7 || e.getSource()==b6 )
                        {
                                if(b9.isSelected()==false && b8.isSelected()==false && b7.isSelected()==false && b6.isSelected()==false)
                                {
                                        toolbar3.setVisible(false);
                                }
                                else
                                {
                                        toolbar3.setVisible(true);
                                }
                        }
}
}

