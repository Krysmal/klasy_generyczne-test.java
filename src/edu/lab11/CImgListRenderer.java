package edu.lab11;

import edu.lab11.generic.CMyLinkedList;

import javax.swing.*;
import java.awt.*;

public class CImgListRenderer extends javax.swing.DefaultListCellRenderer
{
	private static CMyLinkedList<Integer, ImageIcon> list = new CMyLinkedList<>();

	public CImgListRenderer(CMyLinkedList<Integer, ImageIcon> list)
	{
		this.list = list;
	}

	private Icon getIcon(CMyLinkedList<Integer, ImageIcon> l, int idx)
	{
		return l.getByIndex(idx);
	}

	@Override
	public Component getListCellRendererComponent(
			JList list, Object value, int index,
			boolean isSelected, boolean cellHasFocus)
	{
		JLabel label = (JLabel) super.getListCellRendererComponent(
				list, value, index, isSelected, cellHasFocus);
		Icon icon = getIcon(this.list, index);
		label.setText("");
		label.setIcon(icon);
		return label;
	}

}
