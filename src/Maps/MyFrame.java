package Maps;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Geom.Point3D;

public class MyFrame extends JFrame implements MouseListener,ComponentListener {

	private static final long serialVersionUID = 1L;

	private BufferedImage myImage;
	private Map map;
	private ArrayList<Point3D> coords;
	int x = -1;
	int y = -1;

	public MyFrame(String path, Point3D top, Point3D bottom) {
		map = new Map(path, top, bottom, this.getWidth(),this.getHeight());
		myImage = map.getmyImage();
		initGUI();		
		this.addMouseListener(this); 
		this.addComponentListener(this);
		coords = new ArrayList<Point3D>();	
	}

	private void initGUI() {
		MenuBar menuBar = new MenuBar();
		Menu menu1 = new Menu("File");  
		MenuItem item1 = new MenuItem("Load csv");
		MenuItem item2 = new MenuItem("Run");
		MenuItem item3 = new MenuItem("save as kml");
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menuBar.add(menu1);
		this.setMenuBar(menuBar);
		JLabel label = new JLabel(new ImageIcon(myImage));
		add(label);
	}

	public void paint(Graphics g) {
		g.drawImage(myImage, 8,53, this.getWidth()-16,this.getHeight()-61,this);
		Iterator<Point3D> it=coords.iterator();
		while(it.hasNext()) {
			Point3D temp=it.next();
			if(temp.x()!=-1 && temp.y()!=-1) {
				x=(int)temp.x();
				y=(int)temp.y();
				int r = 10;
				x = x - (r / 2);
				y = y - (r / 2);
				map.setHeight(this.getHeight());
				map.setWidth(this.getWidth());
				g.fillOval(x, y, r, r);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		x=e.getX();
		y=e.getY();
		Point3D p=new Point3D(x, y);
		coords.add(p);
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX()+" "+e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		Point3D leftTop = new Point3D(32.105779,35.202331,0);
		Point3D rightBottom = new Point3D(32.10144032,35.211050472,0);
		Point3D leftBottom = new Point3D(32.10166667,35.21222222,0);
		Point3D rightTop = new Point3D(32.10555556,35.20222222);
		Point3D pixStart = new Point3D(697, 616,0);
		MyFrame window = new MyFrame("Ariel1.png", leftTop, leftBottom);

		Map map = new Map("Ariel1.png",rightTop,leftBottom,window.getWidth(),window.getHeight());
		System.out.println("pix to deg: " + map.pixTodeg(pixStart));
		System.out.println("deg to pix: " + map.degToPix(leftBottom));
		System.out.println("deg to pix: " + map.degToPix(rightTop));
		System.out.println("deg to pix: " + map.degToPix(rightBottom));
		System.out.println("deg to pix111: " + map.degToPix(leftTop));

		window.setPreferredSize(new Dimension(window.myImage.getWidth()+100, window.myImage.getHeight()+100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.pack();

	}
}













