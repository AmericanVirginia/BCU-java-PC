package page.pack;

import common.CommonStatic;
import common.pack.PackData.UserPack;
import common.pack.Source.Workspace;
import common.system.VImg;
import common.util.Data;
import common.util.stage.CastleImg;
import common.util.stage.CastleList;
import main.MainBCU;
import page.JBTN;
import page.JL;
import page.JTF;
import page.Page;
import page.support.Exporter;
import page.support.Importer;
import utilpc.Interpret;
import utilpc.UtilPC;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class CastleEditPage extends Page {

	private static final long serialVersionUID = 1L;

	private final JBTN back = new JBTN(0, "back");
	private final JList<CastleImg> jlst = new JList<>();
	private final JScrollPane jspst = new JScrollPane(jlst);
	private final JL jl = new JL();
	private final JL sp = new JL("Boss Spawn");
	private final JTF spwn = new JTF();

	private final JBTN addc = new JBTN(0, "add");
	private final JBTN remc = new JBTN(0, "rem");
	private final JBTN impc = new JBTN(0, "import");
	private final JBTN expc = new JBTN(0, "export");

	private final UserPack pack;
	private final CastleList cas;

	private boolean changing = false;

	public CastleEditPage(Page p, UserPack ac) {
		super(p);
		pack = ac;
		cas = ac.castles;

		ini();
		resized();
	}

	@Override
	protected void resized(int x, int y) {
		setBounds(0, 0, x, y);
		set(back, x, y, 0, 0, 200, 50);
		set(jspst, x, y, 50, 100, 300, 1000);
		set(addc, x, y, 400, 100, 200, 50);
		set(impc, x, y, 400, 200, 200, 50);
		set(expc, x, y, 400, 300, 200, 50);
		set(remc, x, y, 400, 400, 200, 50);
		set(sp, x, y, 400, 500, 200, 50);
		set(spwn, x, y, 400, 550, 200, 50);
		set(jl, x, y, 800, 50, 1000, 1000);

	}

	private void addListeners() {
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(getFront());
			}
		});

		jlst.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (changing || arg0.getValueIsAdjusting())
					return;
				CastleImg img = jlst.getSelectedValue();
				ImageIcon ic = null;
				if (img != null) {
					VImg s = img.img;
					if (s != null)
						ic = UtilPC.getIcon(s);
					spwn.setEnabled(true);
					spwn.setText(img.boss_spawn + "");
				} else {
					spwn.setEnabled(false);
					spwn.setText("Boss Spawn:");
				}
				jl.setIcon(ic);
			}

		});

		spwn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (jlst.isSelectionEmpty())
					return;
				changing = true;

				double firstDouble = CommonStatic.parseDoubleN(spwn.getText());
				int formatDouble = (int) (Interpret.formatDouble(firstDouble, 2) * 100);
				double result = ((int) 25.0 * Math.floor(formatDouble / 25.0)) / 100;

				jlst.getSelectedValue().boss_spawn = result;
				spwn.setText(result + "");
				changing = false;
			}
		});

		addc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getFile("Choose your file", null);
			}
		});

		impc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CastleImg img = jlst.getSelectedValue();
				if (img != null)
					getFile("Choose your file", img);
			}
		});

		expc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CastleImg img = jlst.getSelectedValue();
				if (img != null) {
					VImg s = img.img;
					if (s != null)
						new Exporter((BufferedImage) s.getImg().bimg(), Exporter.EXP_IMG);
				}
			}
		});

		remc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CastleImg img = jlst.getSelectedValue();
				if (img != null) {
					cas.remove(img);
					((Workspace) pack.source).getCasFile(img.getID()).delete();
					changing = true;
					setList();
					changing = false;
				}
			}
		});

	}

	private void getFile(String str, CastleImg vimg) {
		changing = true;
		BufferedImage bimg = new Importer(str).getImg();
		if (bimg == null)
			return;
		if (bimg.getWidth() != 128 && bimg.getHeight() != 256) {
			getFile("Wrong img size. Img size: w=128, h=256", vimg);
			return;
		}

		if (vimg == null) {
			CastleImg castle = new CastleImg(cas.getNextID(CastleImg.class), MainBCU.builder.toVImg(bimg));
			castle.boss_spawn = 828.5;
			cas.add(vimg = castle);
		} else {
			vimg.img.setImg(MainBCU.builder.build(bimg));
		}

		try {
			OutputStream os = ((Workspace) pack.source).writeFile("castles/" + Data.trio(vimg.id.id) + ".png");
			ImageIO.write(bimg, "PNG", os);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
			getFile("Failed to save file", vimg);
			return;
		}

		setList();
		changing = false;
	}

	private void ini() {
		add(back);
		add(jspst);
		add(jl);
		add(sp);
		add(addc);
		add(remc);
		add(impc);
		add(expc);
		add(spwn);
		spwn.setEnabled(false);
		setList();
		addListeners();

	}

	private void setList() {
		int ind = jlst.getSelectedIndex();
		jlst.setListData(cas.toArray());
		if (ind < 0)
			ind = 0;
		if (ind >= cas.size())
			ind = cas.size() - 1;
		jlst.setSelectedIndex(ind);
		CastleImg img = jlst.getSelectedValue();
		if (img != null) {
			jl.setIcon(UtilPC.getIcon(img.img));
			spwn.setEnabled(true);
			spwn.setText(img.boss_spawn + "");
		} else {
			jl.setIcon(null);
			spwn.setEnabled(false);
			spwn.setText("Boss Spawn: ");
		}

	}

}
