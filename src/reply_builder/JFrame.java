package reply_builder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
public class JFrame extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	public static HSSFWorkbook workbook;
	public static HSSFSheet sheet_prewritsentences;
	public static HSSFSheet sheet_db;
	JPanel jpanel;
	JMenuBar menuBar;
	JMenu menuFile;
	JMenuItem menuLoadProject;
	JMenuItem mntmRefresh;
	JList<String> list;
	JTree tree;
	JTree tree2;
	JTextPane paneltexto;
	JButton btnCopy;
	JButton btnSpanish;
	JButton btnEnglish;
	public static void main(String args[]) throws IOException {
		try {
			JFrame frame = new JFrame();
			frame.setVisible(true);
			String content = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/log.txt")));
			InputStream file = new FileInputStream(content);
			if (content.isEmpty() == false){
				workbook = new HSSFWorkbook(file);		        
		        sheet_prewritsentences = workbook.getSheetAt(0);
		        sheet_db = workbook.getSheetAt(1);
		        ActualizarGUI interfaz = frame.new ActualizarGUI();
		        interfaz.ActualizarTextoPanel("Please select a language");
		        interfaz.MostrarBotones();
		        interfaz.ActualizarTituloMainFrame(frame, content.substring(content.lastIndexOf("/") + 1));
		        }
			} 
		catch (Exception e) {
			e.printStackTrace();
			}
		}
	public JFrame() {
		jpanel = new JPanel();
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuLoadProject = new JMenuItem("Load Project");
		mntmRefresh = new JMenuItem("Refresh");
		list = new JList<String>();
		tree = new JTree();
		tree2 = new JTree();
		paneltexto = new JTextPane();
		btnCopy = new JButton("COPY");
		btnSpanish = new JButton("SPANISH");
		btnEnglish = new JButton("ENGLISH");
		ActualizarGUI interfaz = new ActualizarGUI();
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem mntmCopy = new JMenuItem("Copy Title");
		JMenuItem mntmCopyContent = new JMenuItem("Copy Content");
		JMenuItem mntmModify = new JMenuItem("Modify");
        final ArrayList<String []> MegaSubfolders = new ArrayList<String []>();
        	Dimension tamaniopantalla = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, tamaniopantalla.width,tamaniopantalla.height - 30);
		ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(50000);
		list.setBounds(6, 6, 307,655);
		list.setVisible(false);
		tree.setBounds(977, 6, 297, 655);
		tree.setVisible(false);
		tree.setRootVisible(false);
		tree2.setBounds(977, 6, 297, 655);
		tree2.setVisible(false);
		tree2.setRootVisible(false);
		paneltexto.setBounds(325, 6, 640, 584);
		paneltexto.setText("Please load a new Project");
		paneltexto.setEditable(false);
		btnCopy.setBounds(325, 601, 206, 60);
		btnCopy.setVisible(false);
		btnSpanish.setBounds(532, 601, 215, 60);
		btnSpanish.setVisible(false);
		btnSpanish.setOpaque(true);
		btnEnglish.setBounds(750, 601, 215, 60);
		btnEnglish.setVisible(false);
		btnEnglish.setOpaque(true);
		jpanel.setLayout(null);
		setJMenuBar(menuBar);
		menuBar.add(menuFile);
		menuFile.add(menuLoadProject);
		menuFile.add(mntmRefresh);
		setContentPane(jpanel);
		jpanel.add(list);
		jpanel.add(tree);
		jpanel.add(tree2);
		jpanel.add(btnCopy);
		jpanel.add(btnSpanish);
		jpanel.add(btnEnglish);
		jpanel.add(paneltexto);
		interfaz.addPopup(tree, popupMenu);
		popupMenu.add(mntmCopy);
		popupMenu.add(mntmCopyContent);
		popupMenu.add(mntmModify);
		class UndoAction extends AbstractAction {
			private static final long serialVersionUID = 1L;
			private UndoManager manager;
			public UndoAction(UndoManager manager) {
				this.manager = manager;
				}
			public void actionPerformed(ActionEvent evt) {
				try {
					manager.undo();
					} 
				catch (CannotUndoException e) {
					Toolkit.getDefaultToolkit().beep();
					}
				}
			}
		UndoManager manager = new UndoManager();
		paneltexto.getDocument().addUndoableEditListener(manager);
		Action undoAction = new UndoAction(manager);
		paneltexto.registerKeyboardAction(undoAction, KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_FOCUSED);
		tree.addMouseMotionListener(new MouseMotionAdapter() {
					public void mouseMoved(MouseEvent e) {
						tree.setToolTipText(null);
				        TreePath path = tree.getClosestPathForLocation((int) e.getPoint().getX(), (int)e.getPoint().getY());
				        TreePath path2 = tree2.getClosestPathForLocation((int) e.getPoint().getX(), (int)e.getPoint().getY());		        
				        TreePath path3 = tree.getPathForLocation((int) e.getPoint().getX(), (int)e.getPoint().getY());
				        String finalstring = path2.getLastPathComponent().toString();
				        if (path == path3){
				        	if ((path != null) && (path2.getLastPathComponent().toString() != "")) {
					        	finalstring = finalstring.trim();
					        	finalstring = finalstring.replaceAll("\\r\\n|\\r|\\n", "<br>");
					        	tree.setToolTipText("<html><p width=\"450px\">" + finalstring + "</p></html>");
					        }
				        	else if (path2.getLastPathComponent().toString() == ""){
					        	tree.setToolTipText("Empty");
					        }
				        }
					}
				});
		tree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeCollapsed(TreeExpansionEvent event) {
				tree2.collapsePath(tree2.getPathForRow(tree.getRowForPath(event.getPath())));
			}
			public void treeExpanded(TreeExpansionEvent event) {
				tree2.expandPath(tree2.getPathForRow(tree.getRowForPath(event.getPath())));
			}
		});
		tree.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					TreePath path3 = tree2.getClosestPathForLocation((int) e.getPoint().getX(), (int)e.getPoint().getY());
					TreePath path = tree2.getPathForLocation((int)e.getPoint().getX(), (int)e.getPoint().getY());
					TreePath path2 = tree.getPathForLocation((int)e.getPoint().getX(), (int)e.getPoint().getY());
					if (path == path3){
						if ((path != null) && (path3.getLastPathComponent().toString() != "")){
							if (path.getPathCount() == 3){
								boolean gotcha = false;
								for (int i = 0; i < MegaSubfolders.size(); i++){
									for (int j = 0; j < MegaSubfolders.get(i).length; j++){
										if (path2.getLastPathComponent().toString().equals(MegaSubfolders.get(i)[j])){
											gotcha = true;
										}
									}
								}
								if (gotcha != true){
									paneltexto.setText(paneltexto.getText()+path.getLastPathComponent().toString());
									paneltexto.grabFocus();
								}
							} else if (path.getPathCount() == 4){
								paneltexto.setText(paneltexto.getText()+path.getLastPathComponent().toString());
								paneltexto.grabFocus();
							}
						}
					}
				}	
			}
		});
		list.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
	            JList<?> l = (JList<?>)e.getSource();
	            ListModel<?> m = l.getModel();
	            int index = l.locationToIndex(e.getPoint());
	            if ( index>-1 ) {
	                l.setToolTipText(m.getElementAt(index).toString());
	            }
	        }
		});
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					paneltexto.setText(paneltexto.getText()+list.getSelectedValue().toString());
					paneltexto.grabFocus();
				}
			}
		});
		menuLoadProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFrame frame = new JFrame();
				    System.setProperty("apple.awt.fileDialogForDirectories", "false");
				    FileDialog d = new FileDialog(frame);
				    d.setVisible(true);
				    FileInputStream file = null;
					try {
						file = new FileInputStream(System.getProperty(d.getDirectory() + d.getFile()));
						} 
					catch (FileNotFoundException e2) {
						e2.printStackTrace();
					}
				    
			        try {
			        		workbook = new HSSFWorkbook(file);
				        sheet_prewritsentences = workbook.getSheetAt(0);
				        sheet_db = workbook.getSheetAt(1);
						Path path = Paths.get(System.getProperty("user.dir") + "/log.txt");
						byte[] bytes = (d.getDirectory() + d.getFile()).getBytes();
						Files.write(path, bytes);
						interfaz.ActualizarTextoPanel("Please select a language");
			        		interfaz.MostrarBotones();
						interfaz.EsconderPanelesLaterales();
						interfaz.ReiniciarColorBotones();
						String content = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/log.txt")));
				        	interfaz.ActualizarTituloMainFrame(frame, content.substring(content.lastIndexOf("/") + 1));
				        	} 
			        catch (IOException e1) {
			        		e1.printStackTrace();
			        	}
		        }
			});
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String content = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/log.txt")));
					FileInputStream file = new FileInputStream(content);
					if (content.isEmpty() == false){
						workbook = new HSSFWorkbook(file);
				        sheet_prewritsentences = workbook.getSheetAt(0);
				        sheet_db = workbook.getSheetAt(1);
				        interfaz.ActualizarTextoPanel("Please select a language");
				        interfaz.MostrarBotones();
				        interfaz.EsconderPanelesLaterales();
				        interfaz.ReiniciarColorBotones();
				        } else {
				        		System.out.println("Por favor, usa el botónn Load");
				        		}
					}
				catch (Exception e1) {
					e1.printStackTrace();
					}
				}
			});
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection (paneltexto.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
				clpbrd.setContents (stringSelection, null);
				}
			});
		btnSpanish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MegaSubfolders.addAll(interfaz.actualizarArboles(2));
				interfaz.actualizarLista(1);
				interfaz.MostrarPanelesLaterales();
				interfaz.HabilitarPanel();
				btnSpanish.setBackground(Color.green);
				btnEnglish.setBackground(Color.white);
				}
			});
		btnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MegaSubfolders.addAll(interfaz.actualizarArboles(1));
				interfaz.actualizarLista(0);
				interfaz.MostrarPanelesLaterales();
				interfaz.HabilitarPanel();
				btnEnglish.setBackground(Color.green);
				btnSpanish.setBackground(Color.white);
				}
			});
		}
	public class ActualizarGUI {	
		@SuppressWarnings("deprecation")
		public ArrayList<String []> actualizarArboles (int language){
			boolean italic;
			HSSFRow row;
			HSSFRow prev_row;
			HSSFCell cell;
			HSSFCell prev_cell;
			HSSFCell cell_first;
			HSSFCell cell_language;
			HSSFCell cell_folder;
			String cell_first_content = "";
			String cell_language_content = "";
			String cell_folder_content = "";
			HSSFCellStyle style;
			HSSFFont cellFont;
			DefaultTreeModel model1 = (DefaultTreeModel)tree.getModel();
			DefaultTreeModel model2 = (DefaultTreeModel)tree2.getModel();
			DefaultMutableTreeNode rootMain1 = new DefaultMutableTreeNode("Root");
			DefaultMutableTreeNode rootMain2 = new DefaultMutableTreeNode("Root");
			DefaultMutableTreeNode root_carpeta1 = null;
			DefaultMutableTreeNode root_carpeta2 = null;
			ArrayList<DefaultMutableTreeNode> array1 = new ArrayList<DefaultMutableTreeNode>();
			ArrayList<DefaultMutableTreeNode> array2 = new ArrayList<DefaultMutableTreeNode>();
			ArrayList<DefaultMutableTreeNode> invisible_node1 = new ArrayList<DefaultMutableTreeNode>();
			ArrayList<DefaultMutableTreeNode> invisible_node2 = new ArrayList<DefaultMutableTreeNode>();
			ArrayList<String []> MegaSubfolders = new ArrayList<String []>();
			String [] Subfolders = null;
			for (int i = 0; i < sheet_db.getLastRowNum(); i++) {
				row = sheet_db.getRow(i);
				cell_first = row.getCell(0);
				style = cell_first.getCellStyle();
				cellFont = workbook.getFontAt(style.getFontIndex());
				italic = cellFont.getItalic();
				cell_first_content = cell_first.getStringCellValue();
				cell_language = row.getCell(language);
				if (cell_language != null) {
					cell_language_content = cell_language.getStringCellValue();
				} else {
					cell_language_content = "empty";
				}
				cell_folder = row.getCell(3);
				if (cell_folder != null) {
					cell_folder_content = cell_folder.getStringCellValue();
				}		
				if ((i == 0) && (italic == false)){
					System.out.println("The cell located in the top left corner has to be italic");
				} else if ((i == 0) && (italic == true)){
					root_carpeta1 = new DefaultMutableTreeNode(cell_first_content);
					root_carpeta2 = new DefaultMutableTreeNode(cell_first_content);
				} else if ((i > 0) && (italic == false)){
					if (Subfolders == null) {
						root_carpeta1.add(new DefaultMutableTreeNode(cell_first_content));
						root_carpeta2.add(new DefaultMutableTreeNode(cell_language_content));
					} else {
						if (!cell_folder_content.isEmpty()){
							for (int j = 0; j < Subfolders.length; j++){
								if (cell_folder_content.equals(Subfolders[j])){
									array1.get(j).add(new DefaultMutableTreeNode(cell_first_content));
									array2.get(j).add(new DefaultMutableTreeNode(cell_language_content));
								}
							}
							cell_folder_content = "";
						} else {
							invisible_node1.add(new DefaultMutableTreeNode(cell_first_content));
							invisible_node2.add(new DefaultMutableTreeNode(cell_language_content));
						}
					}
				} else if ((i > 0) && (italic == true)){
					prev_row = sheet_db.getRow(i-1);
					prev_cell = prev_row.getCell(0);
					style = prev_cell.getCellStyle();
					cellFont = workbook.getFontAt(style.getFontIndex());
					italic = cellFont.getItalic();
					if (italic){
						cell = row.getCell(1);
						String str = cell.getStringCellValue() + ", Invisible";
						Subfolders = str.split("\\s*,\\s*");
						for (int j = 0; j < Subfolders.length-1; j++){
							array1.add(new DefaultMutableTreeNode(Subfolders[j]));
							array2.add(new DefaultMutableTreeNode(Subfolders[j]));
						}
						MegaSubfolders.add(Subfolders);
					} else {
						if (Subfolders != null){
							for (int j = 0; j < array1.size(); j++){
								root_carpeta1.add(array1.get(j));
								root_carpeta2.add(array2.get(j));
							}
							for (int x = 0; x < invisible_node1.size(); x++){
								root_carpeta1.add(invisible_node1.get(x));
								root_carpeta2.add(invisible_node2.get(x));
							}
							rootMain1.add(root_carpeta1);
							rootMain2.add(root_carpeta2);
							root_carpeta1 = new DefaultMutableTreeNode(cell_first_content);
							root_carpeta2 = new DefaultMutableTreeNode(cell_first_content);
							Subfolders = null;
							invisible_node1.clear();
							invisible_node2.clear();
							array1.clear();
							array2.clear();
						} else {
							rootMain1.add(root_carpeta1);
							rootMain2.add(root_carpeta2);
							root_carpeta1 = new DefaultMutableTreeNode(cell_first_content);
							root_carpeta2 = new DefaultMutableTreeNode(cell_first_content);
						}
					}
				}
			}
			model1.setRoot(rootMain1);
			model2.setRoot(rootMain2);
			return MegaSubfolders;
		}
		public void actualizarLista (int language){
			DefaultListModel<String> DLM = new DefaultListModel<String>();
			HSSFRow row;
			HSSFCell cell;
			for (int i = 0; i < sheet_prewritsentences.getLastRowNum(); i++) {
				row = sheet_prewritsentences.getRow(i);
				cell = row.getCell(language);
				DLM.addElement(cell.getStringCellValue());
			}
			list.setModel(DLM);
		}
		public void ActualizarTituloMainFrame (JFrame frame, String str){
			frame.setTitle(str);
		}
		public void ActualizarTextoPanel (String texto){
			paneltexto.setText(texto);
		}
		public void HabilitarPanel () {
			paneltexto.setText("");
			paneltexto.setEditable(true);
		}
		public void MostrarBotones (){
			btnSpanish.setVisible(true);
			btnEnglish.setVisible(true);
			btnCopy.setVisible(true);
		}
		public void MostrarPanelesLaterales () {
			list.setVisible(true);
			tree.setVisible(true);
		}
		public void EsconderPanelesLaterales (){
			list.setVisible(false);
			tree.setVisible(false);
		}
		public void ReiniciarColorBotones () {
			btnSpanish.setBackground(Color.white);
			btnEnglish.setBackground(Color.white);
		}
		public void addPopup(Component component, final JPopupMenu popup) {
			component.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}
				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}
				private void showMenu(MouseEvent e) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			});
		}
	}
}