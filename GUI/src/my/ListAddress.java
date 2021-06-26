package my;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import af.swing.AfPanel;
import af.swing.layout.AfRowLayout;

// List-Address List
public class ListAddress extends JDialog {

	AfPanel root = new AfPanel();
	AfPanel mPanel = new AfPanel();

	JButton addButton = new JButton("Add");
	JButton editButton = new JButton("Edit");
	JButton deleteButton = new JButton("Delete");

	JTable table = null;

	DefaultTableModel tableModel = new DefaultTableModel();

	JButton closeButton = new JButton("Close");

	private boolean retValue = false;

	public ListAddress(JFrame owner) {
		super(owner, "Addresses", true);
		this.setSize(500, 400);

		// AfPanel root = new AfPanel();
		this.setContentPane(root);
		// root.setLayout(new AfColumnLayout(10));
		root.setLayout(new BorderLayout());
		root.padding(10);

		JLabel label = new JLabel("Addresses List:");
		root.add(label, BorderLayout.NORTH);

		// AfPanel mPanel = new AfPanel();
		root.add(mPanel, BorderLayout.CENTER);

		AfPanel buttonPanel = new AfPanel();
		root.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new AfRowLayout(10));
		buttonPanel.add(new JLabel(), "1w");
		buttonPanel.add(closeButton, "auto");

		AfPanel mbuttonPanel = new AfPanel();
		mPanel.add(mbuttonPanel, BorderLayout.NORTH);
		mbuttonPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
		mbuttonPanel.add(addButton, "auto");
		mbuttonPanel.add(editButton, "auto");
		mbuttonPanel.add(deleteButton, "auto");

		addButton.addActionListener((e) -> {
			onAddNewAddress();
		});

		initTable();

	}

	public boolean exec() {

		Rectangle frmRect = this.getOwner().getBounds();
		int width = this.getWidth();
		int height = this.getHeight();
		int x = frmRect.x + (frmRect.width - width) / 2;
		int y = frmRect.y + (frmRect.height - height) / 2;
		this.setBounds(x, y, width, height);

		this.setVisible(true);

		return retValue;
	}

	private void initTable() {

		table = new JTable(tableModel) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane scrollPane = new JScrollPane(table);
		mPanel.add(scrollPane, BorderLayout.WEST);
		scrollPane.setPreferredSize(new Dimension(300, 200));

		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setRowHeight(30);

		tableModel.addColumn("Address Name");
		tableModel.addColumn("City");
		tableModel.addColumn("Country");

		table.getColumnModel().getColumn(0).setCellRenderer(new IDColumnRender());
		table.getColumnModel().getColumn(0).setPreferredWidth(110);
	}

	private void onAddNewAddress() {
		AddNewAddress ana = new AddNewAddress(this);
		if (ana.exec()) {
//			Person person = ana.getValue();
//			
//			addToDataList (person); 
//			addTableRow( person);		
//			saveData(); 
		}
	}

}
