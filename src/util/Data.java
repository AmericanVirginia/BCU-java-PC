package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.Function;

import io.Reader;
import io.ZipAccess;
import main.Opts;
import util.system.VFile;

public class Data {

	public static final int RARITY_TOT = 6;

	// trait bit filter
	protected static final int TB_WHITE = 1;
	protected static final int TB_RED = 2;
	protected static final int TB_FLOAT = 4;
	protected static final int TB_BLACK = 8;
	protected static final int TB_METAL = 16;
	protected static final int TB_ANGEL = 32;
	protected static final int TB_ALIEN = 64;
	protected static final int TB_ZOMBIE = 128;
	protected static final int TB_RELIC = 256;
	protected static final int TB_EVA = 512;
	protected static final int TB_WITCH = 1024;
	protected static final int TB_INFH = 2048;

	// trait index
	protected static final int TRAIT_WHITE = 0;
	protected static final int TRAIT_RED = 1;
	protected static final int TRAIT_FLOAT = 2;
	protected static final int TRAIT_BLACK = 3;
	protected static final int TRAIT_METAL = 4;
	protected static final int TRAIT_ANGEL = 5;
	protected static final int TRAIT_ALIEN = 6;
	protected static final int TRAIT_ZOMBIE = 7;
	protected static final int TRAIT_RELIC = 8;
	protected static final int TRAIT_EVA = 9;
	protected static final int TRAIT_WITCH = 10;
	protected static final int TRAIT_INFH = 11;

	protected static final int TRAIT_TOT = 12;

	// treasure
	protected static final int T_RED = 0;
	protected static final int T_FLOAT = 1;
	protected static final int T_BLACK = 2;
	protected static final int T_ANGEL = 3;
	protected static final int T_METAL = 4;
	protected static final int T_ALIEN = 5;
	protected static final int T_ZOMBIE = 6;

	// default tech value
	protected static final int[] MLV = new int[] { 30, 30, 30, 30, 30, 30, 30, 10 };

	// tech index
	protected static final int LV_RES = 0;
	protected static final int LV_ACC = 1;
	protected static final int LV_BASE = 2;
	protected static final int LV_WORK = 3;
	protected static final int LV_WALT = 4;
	protected static final int LV_RECH = 5;
	protected static final int LV_CATK = 6;
	protected static final int LV_CRG = 7;

	protected static final int LV_TOT = 8;

	// default treasure value
	protected static final int[] MT = new int[] { 300, 300, 300, 300, 300, 300, 600, 600, 600 };

	// treasure index
	protected static final int T_ATK = 0;
	protected static final int T_DEF = 1;
	protected static final int T_RES = 2;
	protected static final int T_ACC = 3;
	protected static final int T_WORK = 4;
	protected static final int T_WALT = 5;
	protected static final int T_RECH = 6;
	protected static final int T_CATK = 7;
	protected static final int T_BASE = 8;

	protected static final int T_TOT = 9;

	// abi bit filter
	protected static final int AB_GOOD = 1 << 0;
	protected static final int AB_RESIST = 1 << 1;
	protected static final int AB_MASSIVE = 1 << 2;
	protected static final int AB_ONLY = 1 << 3;
	protected static final int AB_EARN = 1 << 4;
	protected static final int AB_BASE = 1 << 5;
	protected static final int AB_METALIC = 1 << 6;
	protected static final int AB_MOVEI = 1 << 7;
	protected static final int AB_WAVES = 1 << 8;
	protected static final int AB_SNIPERI = 1 << 9;
	public static final int AB_TIMEI = 1 << 10;
	protected static final int AB_GHOST = 1 << 11;
	protected static final int AB_POII = 1 << 12;
	protected static final int AB_ZKILL = 1 << 13;
	protected static final int AB_WKILL = 1 << 14;
	public static final int AB_GLASS = 1 << 15;
	protected static final int AB_THEMEI = 1 << 16;
	protected static final int AB_EKILL = 1 << 17;
	protected static final int AB_SEALI = 1 << 18;
	protected static final int AB_IMUSW = 1 << 19;
	protected static final int AB_RESISTS = 1 << 20;
	protected static final int AB_MASSIVES = 1 << 21;

	// 0111 1010 1110 0001 0111 1111
	@Deprecated
	public static final int AB_ELIMINATOR = 0x7ae17f;

	// abi index
	protected static final int ABI_GOOD = 0;
	protected static final int ABI_RESIST = 1;
	protected static final int ABI_MASSIVE = 2;
	protected static final int ABI_ONLY = 3;
	protected static final int ABI_EARN = 4;
	protected static final int ABI_BASE = 5;
	protected static final int ABI_METALIC = 6;
	protected static final int ABI_MOVEI = 7;
	protected static final int ABI_WAVES = 8;
	protected static final int ABI_SNIPERI = 9;
	protected static final int ABI_TIMEI = 10;
	protected static final int ABI_GHOST = 11;
	protected static final int ABI_POII = 12;
	protected static final int ABI_ZKILL = 13;
	protected static final int ABI_WKILL = 14;
	protected static final int ABI_GLASS = 15;
	protected static final int ABI_THEMEI = 16;
	protected static final int ABI_EKILL = 17;
	protected static final int ABI_SEALI = 18;
	protected static final int ABI_IMUSW = 19;
	protected static final int ABI_RESISTS = 20;
	protected static final int ABI_MASSIVES = 21;

	protected static final int ABI_TOT = 30;// 20 currently

	// proc index
	protected static final int P_KB = 0;
	protected static final int P_STOP = 1;
	protected static final int P_SLOW = 2;
	protected static final int P_CRIT = 3;
	protected static final int P_WAVE = 4;
	protected static final int P_WEAK = 5;
	protected static final int P_BREAK = 6;
	protected static final int P_WARP = 7;
	protected static final int P_CURSE = 8;
	protected static final int P_STRONG = 9;
	protected static final int P_LETHAL = 10;
	protected static final int P_BORROW = 11;
	protected static final int P_REVIVE = 12;

	protected static final int P_IMUKB = 13;
	protected static final int P_IMUSTOP = 14;
	protected static final int P_IMUSLOW = 15;
	protected static final int P_IMUWAVE = 16;
	protected static final int P_IMUWEAK = 17;
	protected static final int P_IMUWARP = 18;
	protected static final int P_IMUCURSE = 19;

	protected static final int P_SNIPER = 20;
	protected static final int P_TIME = 21;
	protected static final int P_SEAL = 22;
	/** 0:prob, 1:ID, 2:location, 3: buff, 4:conf, 5:time */
	/**
	 * +0: direct, +1: warp, +2:burrow, +4:disregard limit, +8: fix buff, +16: same
	 * health, +32: diff layer, +64 on hit, +128 on kill
	 */
	protected static final int P_SUMMON = 23;
	/**
	 * 0:prob, 1:speed, 2:width (left to right), 3:time, 4:origin (center), 5:itv
	 */
	protected static final int P_MOVEWAVE = 24;
	/** 0:prob, 1:time (-1 means infinite), 2:ID */
	protected static final int P_THEME = 25;
	/** 0:prob, 1:time, 2:dmg, 3:itv */
	protected static final int P_POISON = 26;

	protected static final int PROC_TOT = 30;// 27
	protected static final int PROC_WIDTH = 6;

	protected static final int WT_WAVE = 1;
	protected static final int WT_MOVE = 2;
	protected static final int WT_CANN = 2;

	protected static final int PC_P = 0, PC_AB = 1, PC_BASE = 2, PC_IMU = 3, PC_TRAIT = 4;

	protected static final int PC2_HP = 0;
	protected static final int PC2_ATK = 1;
	protected static final int PC2_SPEED = 2;
	protected static final int PC2_COST = 3;
	protected static final int PC2_CD = 4;

	protected static final int[][] PC_CORRES = new int[][] { // NP value table
			{ -1, 0 }, // 0: TODO
			{ 0, P_WEAK }, // 1: weak, reversed health
			{ 0, P_STOP }, // 2: stop
			{ 0, P_SLOW }, // 3: slow
			{ -1, 0 }, // 4: TODO
			{ -1, 0 }, // 5: TODO
			{ -1, 0 }, // 6: TODO
			{ -1, 0 }, // 7: TODO
			{ 0, P_KB }, // 8: kb
			{ -1, 0 }, // 9: TODO
			{ 0, P_STRONG }, // 10: berserker, reversed health
			{ 0, P_LETHAL }, // 11: lethal
			{ -1, 0 }, // 12: TODO
			{ 0, P_CRIT }, // 13: crit
			{ 1, AB_ZKILL }, // 14: zkill
			{ 0, P_BREAK }, // 15: break
			{ 1, AB_EARN }, // 16: 2x income
			{ 0, P_WAVE }, // 17: wave
			{ 0, P_IMUWEAK }, // 18: res weak
			{ 0, P_IMUSTOP }, // 19: res stop
			{ 0, P_IMUSLOW }, // 20: res slow
			{ 0, P_IMUKB }, // 21: res kb
			{ 0, P_IMUWAVE }, // 22: res wave
			{ -1, 0 }, // 23: TODO
			{ -1, 0 }, // 24: TODO
			{ 2, PC2_COST }, // 25: reduce cost
			{ 2, PC2_CD }, // 26: reduce cooldown
			{ 2, PC2_SPEED }, // 27: inc speed
			{ -1, 0 }, // 28: TODO
			{ 3, P_IMUCURSE }, // 29: imu curse
			{ 0, P_IMUCURSE }, // 30: res curse
			{ 2, PC2_ATK }, // 31: inc ATK
			{ 2, PC2_HP }, // 32: inc HP
			{ -1, 0 }, // 33: TODO
			{ -1, 0 }, // 34: TODO
			{ -1, 0 }, // 35: TODO
			{ -1, 0 }, // 36: TODO
			{ 4, TB_ANGEL }, // 37: targeting angle
			{ 4, TB_ALIEN }, // 38: targeting alien
			{ 4, TB_ZOMBIE }, // 39: targeting zombie
			{ 4, TB_RELIC } }; // 40: targeting relic

	// foot icon index used in battle
	protected static final int INV = -1;
	protected static final int INVWARP = -2;
	protected static final int STPWAVE = -3;
	protected static final int BREAK_ABI = -4;
	protected static final int BREAK_ATK = -5;
	protected static final int BREAK_NON = -6;

	// Combo index
	public static final int C_ATK = 0;
	public static final int C_DEF = 1;
	public static final int C_SPE = 2;
	public static final int C_GOOD = 14;
	public static final int C_MASSIVE = 15;
	public static final int C_RESIST = 16;
	public static final int C_KB = 17;
	public static final int C_SLOW = 18;
	public static final int C_STOP = 19;
	public static final int C_WEAK = 20;
	public static final int C_STRONG = 21;
	public static final int C_WKILL = 22;
	public static final int C_EKILL = 23;
	public static final int C_CRIT = 24;
	public static final int C_C_INI = 3;
	public static final int C_C_ATK = 6;
	public static final int C_C_SPE = 7;
	public static final int C_BASE = 10;
	public static final int C_M_INI = 5;
	public static final int C_M_LV = 4;
	public static final int C_M_MAX = 9;
	public static final int C_RESP = 11;
	public static final int C_MEAR = 12;
	public static final int C_XP = 13;// TODO abandoned

	protected static final int C_TOT = 25;

	// Effects Anim index
	protected static final int A_KB = 29;
	protected static final int A_CRIT = 28;
	protected static final int A_SHOCKWAVE = 27;
	protected static final int A_ZOMBIE = 26;
	protected static final int A_EFF_INV = 18;
	protected static final int A_EFF_DEF = 19;// TODO unused
	protected static final int A_Z_STRONG = 20;
	protected static final int A_B = 21;
	protected static final int A_E_B = 22;
	protected static final int A_W = 23;
	protected static final int A_W_C = 24;
	protected static final int A_CURSE = 25;
	protected static final int A_DOWN = 0;
	protected static final int A_UP = 2;
	protected static final int A_SLOW = 4;
	protected static final int A_STOP = 6;
	protected static final int A_SHIELD = 8;
	protected static final int A_FARATTACK = 10;
	protected static final int A_WAVE_INVALID = 12;
	protected static final int A_WAVE_STOP = 14;
	protected static final int A_WAVEGUARD = 16;// TODO unused
	protected static final int A_E_DOWN = 1;
	protected static final int A_E_UP = 3;
	protected static final int A_E_SLOW = 5;
	protected static final int A_E_STOP = 7;
	protected static final int A_E_SHIELD = 9;
	protected static final int A_E_FARATTACK = 11;
	protected static final int A_E_WAVE_INVALID = 13;
	protected static final int A_E_WAVE_STOP = 15;
	protected static final int A_E_WAVEGUARD = 17;// TODO unused
	protected static final int A_SNIPER = 30;
	protected static final int A_U_ZOMBIE = 31;
	protected static final int A_U_B = 32;
	protected static final int A_U_E_B = 33;
	protected static final int A_SEAL = 34;
	protected static final int A_POISON = 35;

	protected static final int A_TOT = 36;

	// atk type index used in filter page
	protected static final int ATK_SINGLE = 0;
	protected static final int ATK_AREA = 1;
	protected static final int ATK_LD = 2;
	protected static final int ATK_OMNI = 4;

	protected static final int ATK_TOT = 6;

	// base and canon level
	protected static final int BASE_H = 0;
	protected static final int BASE_SLOW = 1;
	protected static final int BASE_WALL = 2;
	protected static final int BASE_STOP = 3;
	protected static final int BASE_WATER = 4;
	protected static final int BASE_GROUND = 5;
	protected static final int BASE_BARRIER = 6;

	protected static final int BASE_TOT = 7;

	// touchable ID
	public static final int TCH_N = 1;
	public static final int TCH_KB = 2;
	public static final int TCH_UG = 4;
	public static final int TCH_CORPSE = 8;
	public static final int TCH_SOUL = 16;
	public static final int TCH_EX = 32;

	// After this line all number is game data

	protected static final String[] A_PATH = new String[] { "down", "up", "slow", "stop", "shield", "farattack",
			"wave_invalid", "wave_stop", "waveguard" };

	public static final int INT_KB = 0, INT_HB = 1, INT_SW = 2, INT_ASS = 3, INT_WARP = 4;
	protected static final int[] KB_PRI = new int[] { 2, 4, 5, 1, 3 };
	public static final int[] KB_TIME = new int[] { 11, 23, 47, 11, -1 };
	public static final int[] KB_DIS = new int[] { 165, 345, 705, 55, -1 };

	protected static final int W_E_INI = -33;
	protected static final int W_U_INI = -67;
	protected static final int W_PROG = 200;
	protected static final int W_E_WID = 500;
	protected static final int W_U_WID = 400;
	protected static final int W_TIME = 3;

	protected static final int E_IMU = -1;
	protected static final int E_IWAVE = -2;
	protected static final int E_SWAVE = -3;

	protected static final int[] NYPRE = new int[] { 18, 2, -1, 28, 37, 18, 10 };// TODO
	protected static final int[] NYRAN = new int[] { 710, 600, -1, 500, 500, 710, 100 };// TODO

	protected static final int SNIPER_CD = 300;// TODO
	protected static final int SNIPER_PRE = 5;// TODO
	protected static final int SNIPER_POS = -500;// TODO

	protected static final int REVIVE_SHOW_TIME = 16;

	protected static final String[] SUFX = new String[] { "f", "c", "s" };

	public static int getVer(String ver) {
		int ans = 0;
		String[] strs = ver.split("\\.");
		for (String str : strs) {
			ans *= 100;
			ans += Integer.parseInt(str);
		}
		return ans;
	}

	public static Queue<String> readLine(VFile f) {
		if (f == null)
			return null;
		if (f.getRoot() != null && f.getRoot().type == 1)
			try {
				return ZipAccess.readLine(new String(f.data));
			} catch (IOException e1) {
				e1.printStackTrace();
				Opts.backupErr("cannot read " + new String(f.data));
				return null;
			}
		Queue<String> ans = new ArrayDeque<>();
		BufferedReader reader = null;
		try {
			InputStreamReader isr = new InputStreamReader(f.getData(), "UTF-8");
			reader = new BufferedReader(isr);
			String temp = null;
			while ((temp = reader.readLine()) != null)
				ans.add(temp);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ans;
	}

	public static String revVer(int ver) {
		return ver / 1000000 % 100 + "-" + ver / 10000 % 100 + "-" + ver / 100 % 100 + "-" + ver % 100;
	}

	public static String trio(int i) {
		String str = "";
		if (i < 100)
			str += "0";
		if (i < 10)
			str += "0";
		return str + i;
	}

	protected static String hex(int id) {
		return trio(id / 1000) + trio(id % 1000);
	}

	protected static <T> T readSave(String path, Function<Queue<String>, T> func) {
		VFile f = VFile.getFile(path);
		VFile b = Reader.alt == null ? null : Reader.alt.getVFile(path);
		int ind = 0;
		while (true) {
			if (f != null) {
				T ic = null;
				Queue<String> qs = readLine(f);
				if (qs != null)
					try {
						ic = func.apply(qs);
					} catch (Exception e) {
						e.printStackTrace();
						ic = null;
					}
				if (ic != null)
					return ic;
			}
			if (b == null)
				break;
			if (b.child == null)
				if (b != f)
					f = b;
				else
					break;
			else if (ind < b.child.size())
				f = b.child.get(ind++);
			else
				break;
		}
		Opts.animErr(f.getName());
		return func.apply(null);
	}

}
