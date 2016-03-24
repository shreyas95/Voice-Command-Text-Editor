
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shreyas
 */
public class FileMenu 
{
    private int savefirst = 0;
    private int newoption = 1;
    private String open = "",save="";
    
    public void submenu_saveas(final JTextPane a1)
    {
        System.out.println("saveas");
        final JFrame frame=new JFrame("Saving the file");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane=frame.getContentPane();

        final JLabel directoryLabel=new JLabel(" ");
        directoryLabel.setFont(new Font("Serif", Font.BOLD,36));
        contentPane.add(directoryLabel,BorderLayout.NORTH);

        final JLabel filenameLabel = new JLabel(" ");
        filenameLabel.setFont(new Font("Serif", Font.BOLD,36));
        contentPane.add(filenameLabel,BorderLayout.SOUTH);

        JFileChooser filechooser=new JFileChooser("");
        filechooser.setApproveButtonText("Save");
        contentPane.add(filechooser,BorderLayout.CENTER);
        ActionListener actionlistener=new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) 
                {
                        // TODO Auto-generated method stub
                        JFileChooser thefilechooser=(JFileChooser)e.getSource();
                        if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
                        {
                                File selectedfile = thefilechooser.getSelectedFile();
                                directoryLabel.setText(selectedfile.getParent());
                                filenameLabel.setText(selectedfile.getName());
                                save=directoryLabel.getText() + "\\" + filenameLabel.getText();
                                System.out.println("Path selected for saving is :- "+save);
                                String d="";
                                d=a1.getText();
                                byte b []=d.getBytes();
                                System.out.println(d);
                                File f=new File(save);
                                try {
                                        FileOutputStream fout=new FileOutputStream(f);
                                        fout.write(b);
                                        fout.close();
                                        savefirst=1;
                                } catch (Exception e1) 
                                {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }
                                frame.setVisible(false);
//                                setTitle("MyTextEditor "+"("+save+")");
                        }else if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
                        {
                                directoryLabel.setText(" ");
                                filenameLabel.setText(" ");
                                frame.setVisible(false);
                        }
                }
        };
        filechooser.addActionListener(actionlistener);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void submenu_save(final JTextPane a1)
    {
        System.out.println("save");
        if(savefirst==0)
        {
            final JFrame frame=new JFrame("Saving the file");
            Container contentPane=frame.getContentPane();

            final JLabel directoryLabel=new JLabel(" ");
            directoryLabel.setFont(new Font("Serif", Font.BOLD,36));
            contentPane.add(directoryLabel,BorderLayout.NORTH);

            final JLabel filenameLabel = new JLabel(" ");
            filenameLabel.setFont(new Font("Serif", Font.BOLD,36));
            contentPane.add(filenameLabel,BorderLayout.SOUTH);

            JFileChooser filechooser=new JFileChooser("");
            filechooser.setApproveButtonText("Save");
            contentPane.add(filechooser,BorderLayout.CENTER);
            ActionListener actionlistener=new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) 
                {

                        // TODO Auto-generated method stub
                        JFileChooser thefilechooser=(JFileChooser)e.getSource();
                        if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
                        {
                                File selectedfile = thefilechooser.getSelectedFile();
                                directoryLabel.setText(selectedfile.getParent());
                                filenameLabel.setText(selectedfile.getName());
                                save=directoryLabel.getText() + "\\" + filenameLabel.getText();
                                System.out.println("Path selected for saving is :- "+save);
                                String d="";
                                d=a1.getText();
                                byte b []=d.getBytes();
                                System.out.println(d);
                                File f=new File(save);
                                try {
                                        FileOutputStream fout=new FileOutputStream(f);
                                        fout.write(b);
                                        fout.close();
                                        savefirst=1;
                                } catch (Exception e1) 
                                {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }
                                frame.setVisible(false);
//                                setTitle("MyTextEditor "+"("+save+")");
                        }else if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
                        {
                                directoryLabel.setText(" ");
                                filenameLabel.setText(" ");
                                frame.setVisible(false);
                        }

                }
        };
        filechooser.addActionListener(actionlistener);
        frame.pack();
        frame.setVisible(true);
        }
        else if(savefirst==1)
        {
                System.out.println("Path selected for saving is :- "+save);
                String d="";
                d=a1.getText();
                byte b []=d.getBytes();
                System.out.println(d);
                File f=new File(save);
                try {
                        FileOutputStream fout=new FileOutputStream(f);
                        fout.write(b);
                        fout.close();
                } catch (Exception e1) 
                {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                }
                //this.setTitle("MyTextEditor "+"("+save+")");
	}
    }
    
    public void submenu_open(final JTextPane a1)
    {
        			System.out.println("open");
			final JFrame frame=new JFrame("OPEN");
			Container contentPane=frame.getContentPane();
			
			final JLabel directoryLabel=new JLabel(" ");
			directoryLabel.setFont(new Font("Serif", Font.BOLD,36));
			contentPane.add(directoryLabel,BorderLayout.NORTH);
			
			final JLabel filenameLabel = new JLabel(" ");
			filenameLabel.setFont(new Font("Serif", Font.BOLD,36));
			contentPane.add(filenameLabel,BorderLayout.SOUTH);
			
			JFileChooser filechooser=new JFileChooser(".");
			filechooser.setControlButtonsAreShown(true);//       control buttons
			contentPane.add(filechooser,BorderLayout.CENTER);
			
			ActionListener actionlistener=new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					JFileChooser thefilechooser=(JFileChooser)e.getSource();
					if(e.getActionCommand().equals(JFileChooser.APPROVE_SELECTION))
					{
						File selectedfile = thefilechooser.getSelectedFile();
						directoryLabel.setText(selectedfile.getParent());
						filenameLabel.setText(selectedfile.getName());
                                        
						open=directoryLabel.getText() +"\\"+ filenameLabel.getText();
						System.out.println("File which you"+open);
						FileInputStream input1 = null;
						try {
							input1 = new FileInputStream(""+open);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int c=0;
						String d="";
						do
						{
							try {
								c=(byte) input1.read();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.print((char)c);
							d=d+(char)c;
							a1.setText(d);
						}while(c!=-1);
						frame.setVisible(false);
//                                                setTitle("MyTextEditor "+"("+open+")");
					}else if(e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
					{
						directoryLabel.setText(" ");
						filenameLabel.setText(" ");
                                                frame.setVisible(false);
					}		
				}
				
			};
			
			filechooser.addActionListener(actionlistener);
			frame.pack();
			frame.setVisible(true);
		
    }
    
    public void submenu_new(JTextPane a1)
    {
        		savefirst=0;
			if(newoption==1)
			{
			System.out.println("new");
                       
			new mytexteditor1();//           love you obsssssssss!!!!!!
			}
			else
			{
				a1.setVisible(true);
				newoption=1;
			}
    }
    
}
