/*
 * Copyright (c) 2024, Khronos Group and Contributors
 * Copyright (c) 2021, Shukant Pal and Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.khronos.ktx;

/**
 * Enumerators for specifying the transcode target format.<br>
 * <br>
 * For BasisU/ETC1S format, Opaque and alpha here refer to 2 separate RGB
 * images, a.k.a slices within the BasisU compressed data. For UASTC format they
 * refer to the RGB and the alpha components of the UASTC data. If the original
 * image had only 2 components, R will be in the opaque portion and G in the
 * alpha portion. The R value will be replicated in the RGB components. In the
 * case of BasisU the G value will be replicated in all 3 components of the
 * alpha slice. If the original image had only 1 component it's value is
 * replicated in all 3 components of the opaque portion and there is no alpha.<br>
 * <br>
 * You should not transcode sRGB encoded data to <code>KTX_TTF_BC4_R</code>,
 * <code>KTX_TTF_BC5_RG</code>, <code>KTX_TTF_ETC2_EAC_R{,G}11</code>, <code>KTX_TTF_RGB565</code>,
 * <code>KTX_TTF_BGR565</code> or <code>KTX_TTF_RGBA4444</code> formats as neither OpenGL nor
 * Vulkan support sRGB variants of these. Doing sRGB decoding in the shader will
 * not produce correct results if any texture filtering is being used.
 */
public class KtxTranscodeFormat {

	/**
	 * Opaque only. Returns RGB or alpha data, if
	 * {@link KtxTranscodeFlagBits#KTX_TF_TRANSCODE_ALPHA_DATA_TO_OPAQUE_FORMATS} flag is specified.
	 */
	public static final int KTX_TTF_ETC1_RGB = 0;

	/**
	 * Opaque+alpha. EAC_A8 block followed by an ETC1 block. The alpha channel will
	 * be opaque for textures without an alpha channel.
	 */
	public static final int KTX_TTF_ETC2_RGBA = 1;

	/**
	 * Opaque only, no punchthrough alpha support yet. Returns RGB or alpha data, if
	 * {@link KtxTranscodeFlagBits#KTX_TF_TRANSCODE_ALPHA_DATA_TO_OPAQUE_FORMATS} flag is specified.
	 */
	public static final int KTX_TTF_BC1_RGB = 2;

	/**
	 * Opaque+alpha. BC4 block with alpha followed by a BC1 block. The alpha channel
	 * will be opaque for textures without an alpha channel.
	 */
	public static final int KTX_TTF_BC3_RGBA = 3;

	/**
	 * One BC4 block. R = opaque.g or alpha.g, if
	 * {@link KtxTranscodeFlagBits#KTX_TF_TRANSCODE_ALPHA_DATA_TO_OPAQUE_FORMATS} flag is specified.
	 */
	public static final int KTX_TTF_BC4_R = 4;

	/**
	 * Two BC4 blocks, R=opaque.g and G=alpha.g The texture should have an alpha
	 * channel (if not G will be all 255's. For tangent space normal maps.
	 */
	public static final int KTX_TTF_BC5_RG = 5;

	/**
	 * RGB or RGBA mode 5 for ETC1S, modes 1, 2, 3, 4, 5, 6, 7 for UASTC.
	 */
	public static final int KTX_TTF_BC7_RGBA = 6;

	/**
	 * Opaque only. Returns RGB or alpha data, if
	 * {@link KtxTranscodeFlagBits#KTX_TF_TRANSCODE_ALPHA_DATA_TO_OPAQUE_FORMATS} flag is specified.
	 */
	public static final int KTX_TTF_PVRTC1_4_RGB = 8;

	/**
	 * Opaque+alpha. Most useful for simple opacity maps. If the texture doesn't
	 * have an alpha channel PVRTC1_4_RGB will be used instead. Lowest quality of
	 * any supported texture format.
	 */
	public static final int KTX_TTF_PVRTC1_4_RGBA = 9;

	/**
	 * Opaque+alpha, ASTC 4x4. The alpha channel will be opaque for textures without
	 * an alpha channel. The transcoder uses RGB/RGBA/L/LA modes, void extent, and
	 * up to two ([0,47] and [0,255]) endpoint precisions.
	 */
	public static final int KTX_TTF_ASTC_4x4_RGBA = 10;

	/**
	 * Opaque-only. Almost BC1 quality, much faster to transcode and supports
	 * arbitrary texture dimensions (unlike PVRTC1 RGB).
	 */
	public static final int KTX_TTF_PVRTC2_4_RGB = 18;

	/**
	 * Opaque+alpha. Slower to transcode than cTFPVRTC2_4_RGB. Premultiplied alpha
	 * is highly recommended, otherwise the color channel can leak into the alpha
	 * channel on transparent blocks.
	 */
	public static final int KTX_TTF_PVRTC2_4_RGBA = 19;

	/**
	 * R only (ETC2 EAC R11 unsigned). R = opaque.g or alpha.g, if
	 * {@link KtxTranscodeFlagBits#KTX_TF_TRANSCODE_ALPHA_DATA_TO_OPAQUE_FORMATS} flag is specified.
	 */
	public static final int KTX_TTF_ETC2_EAC_R11 = 20;

	/**
	 * RG only (ETC2 EAC RG11 unsigned), R=opaque.g, G=alpha.g. The texture should
	 * have an alpha channel (if not G will be all 255's. For tangent space normal
	 * maps.
	 */
	public static final int KTX_TTF_ETC2_EAC_RG11 = 21;

	/**
	 * 32bpp RGBA image stored in raster (not block) order in memory, R is first
	 * byte, A is last byte.
	 */
	public static final int KTX_TTF_RGBA32 = 13;

	/**
	 * 16bpp RGB image stored in raster (not block) order in memory, R at bit
	 * position 11.
	 */
	public static final int KTX_TTF_RGB565 = 14;

	/**
	 * 16bpp RGB image stored in raster (not block) order in memory, R at bit
	 * position 0.
	 */
	public static final int KTX_TTF_BGR565 = 15;

	/**
	 * 16bpp RGBA image stored in raster (not block) order in memory, R at bit
	 * position 12, A at bit position 0.
	 */
	public static final int KTX_TTF_RGBA4444 = 16;

	/**
	 * Automatically selects ETC1_RGB or ETC2_RGBA according to presence of alpha.
	 */
	public static final int KTX_TTF_ETC = 22;

	/**
	 * Automatically selects BC1_RGB or BC3_RGBA according to presence of alpha.
	 */
	public static final int KTX_TTF_BC1_OR_3 = 23;

	public static final int KTX_TTF_NOSELECTION = 0x7fffffff;

	/**
	 * Returns a string representation of the given transcode format
	 *
	 * @param n The transcode format
	 * @return A string representation of the given transcode format
	 */
	public static String stringFor(int n) {
		switch (n) {
		case KTX_TTF_ETC1_RGB: return "KTX_TTF_ETC1_RGB";
		case KTX_TTF_ETC2_RGBA: return "KTX_TTF_ETC2_RGBA";
		case KTX_TTF_BC1_RGB: return "KTX_TTF_BC1_RGB";
		case KTX_TTF_BC3_RGBA: return "KTX_TTF_BC3_RGBA";
		case KTX_TTF_BC4_R: return "KTX_TTF_BC4_R";
		case KTX_TTF_BC5_RG: return "KTX_TTF_BC5_RG";
		case KTX_TTF_BC7_RGBA: return "KTX_TTF_BC7_RGBA";
		case KTX_TTF_PVRTC1_4_RGB: return "KTX_TTF_PVRTC1_4_RGB";
		case KTX_TTF_PVRTC1_4_RGBA: return "KTX_TTF_PVRTC1_4_RGBA";
		case KTX_TTF_ASTC_4x4_RGBA: return "KTX_TTF_ASTC_4x4_RGBA";
		case KTX_TTF_PVRTC2_4_RGB: return "KTX_TTF_PVRTC2_4_RGB";
		case KTX_TTF_PVRTC2_4_RGBA: return "KTX_TTF_PVRTC2_4_RGBA";
		case KTX_TTF_ETC2_EAC_R11: return "KTX_TTF_ETC2_EAC_R11";
		case KTX_TTF_ETC2_EAC_RG11: return "KTX_TTF_ETC2_EAC_RG11";
		case KTX_TTF_RGBA32: return "KTX_TTF_RGBA32";
		case KTX_TTF_RGB565: return "KTX_TTF_RGB565";
		case KTX_TTF_BGR565: return "KTX_TTF_BGR565";
		case KTX_TTF_RGBA4444: return "KTX_TTF_RGBA4444";
		case KTX_TTF_ETC: return "KTX_TTF_ETC";
		case KTX_TTF_BC1_OR_3: return "KTX_TTF_BC1_OR_3";
		case KTX_TTF_NOSELECTION: return "KTX_TTF_NOSELECTION";
		}
		return "[Unknown KtxTranscodeFormat]";
	}

	/**
	 * Private constructor to prevent instantiation
	 */
	private KtxTranscodeFormat() {
		// Prevent instantiation
	}

}
