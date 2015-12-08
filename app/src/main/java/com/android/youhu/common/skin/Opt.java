package com.android.youhu.common.skin;

public class Opt {
		public static final int RES_NONE					= 0;
		public static final int RES_APPOINT_PARENT			= -1;
		
		/** 为定义 */
		public static final int NONE						= 0x0;
		/** 修改text颜色 */
		public static final int TEXT_COLOR					= 0x1 << 1;
		/** 修改背景颜色 */
		public static final int BG_COLOR					= 0x1 << 2;
		/** 修改背景资源 */
		public static final int BG_DRAWABLE					= 0x1 << 3;
		/** 修改src drawable颜色 */
		public static final int SRC_DRAWABLE				= 0x1 << 4;
		/** 修改src color颜色 */
		public static final int SRC_COLOR					= 0x1 << 5;
		/** 修改compound drawable颜色 */
		public static final int COMPOUND_DRAWABLE			= 0x1 << 6;
		/** 特殊控件 */
		public static final int SPECIFY						= 0x1 << 7;
		/** Drawable */
		public static final int DRAWABLE					= 0x1 << 8;
		/** listview selector */
		public static final int LISTVIEW_SELECTOR			= 0x1 << 9;
		/** radio, check... */
		public static final int COMPOUND_BUTTON				= 0x1 << 10;
		/** ProgressBar, SeekBar */
		public static final int PROGRESS					= 0x1 << 11;
		/** viewGroup */
		public static final int VIEW_GROUP					= 0x1 << 12;
		/** spand, CharacterStyle */
		public static final int CHARACTER_STYLE				= 0x1 << 13;
		
		public static boolean is(int value, int opt){
			return (value & opt) == opt;
		}
	}


