//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.aspose.words;

import com.aspose.words.internal.zz4W;
import com.aspose.words.internal.zzW7f;
import com.aspose.words.internal.zzWCy;
import com.aspose.words.internal.zzWYw;
import com.aspose.words.internal.zzWb2;
import com.aspose.words.internal.zzXXi;
import com.aspose.words.internal.zzZdf;
import com.aspose.words.internal.zzZif;
import com.aspose.words.internal.zzZvP;
import java.io.File;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class zzwH {
    private zzZif zzW0y;
    private String[] zzYt8;
    private String zzWhl;
    private Date zzZ2e;
    private Date zzW5P;
    private byte[] zzX5u;
    private static zzZif zzoy = zzZif.zzYi2();
    private static zzwH zzYpB = null;
    static long zzXQY = 0L;
    private static long zzXW5 = 7133812862090241280L;
    private static zzWb2<String> zzZBh;
    private static zzWb2<String> zzzH;
    private static final String zzYUh = zzZif.zzYi2().zzXfR(new byte[]{110, 48, 117, 82, 78, 119, 100, 69, 88, 100, 76, 119, 88, 102, 55, 66, 119, 112, 54, 54, 55, 71, 77, 49, 57, 83, 103, 51, 56, 87, 78, 109, 74, 122, 118, 55, 104, 117, 83, 51, 52, 102, 65, 86, 79, 69, 102, 66, 120, 112, 72, 116, 67, 76, 116, 111, 109, 121, 49, 118, 111, 80, 117, 101, 120, 107, 101, 89, 49, 51, 52, 75, 49, 52, 118, 73, 48, 113, 107, 80, 120, 118, 121, 49, 90, 90, 52, 71, 49, 79, 67, 75, 51, 118, 109, 79, 98, 100, 55, 115, 113, 117, 85, 122, 52, 66, 88, 120, 108, 72, 117, 79, 52, 98, 115, 86, 79, 84, 122, 68, 74, 68, 77, 53, 72, 87, 108, 113, 67, 82, 49, 66, 72, 72, 71, 99, 108, 106, 84, 121, 115, 50, 71, 86, 111, 105, 76, 118, 48, 116, 114, 71, 113, 118, 53, 119, 81, 43, 66, 88, 105, 117, 111, 110, 89, 66, 115, 48, 88, 117, 61, 48});
    private static final String zzY8i = zzZif.zzYi2().zzXfR(new byte[]{75, 113, 113, 70, 104, 113, 111, 70, 90, 118, 69, 118, 67, 89, 101, 72, 51, 68, 56, 78, 121, 79, 65, 43, 120, 117, 79, 114, 56, 65, 86, 99, 73, 118, 52, 108, 52, 117, 73, 113, 77, 66, 108, 81, 101, 122, 71, 106, 88, 121, 105, 107, 85, 84, 111, 106, 121, 114, 122, 117, 104, 108, 83, 108, 98, 50, 56, 71, 70, 48, 109, 71, 115, 70, 43, 72, 85, 119, 98, 75, 105, 89, 107, 69, 47, 87, 122, 52, 101, 115, 67, 90, 47, 86, 106, 69, 117, 47, 98, 115, 54, 117, 72, 121, 67, 65, 75, 81, 47, 79, 67, 83, 51, 121, 89, 112, 54, 119, 80, 56, 118, 99, 48, 57, 112, 49, 121, 118, 85, 119, 111, 110, 89, 97, 73, 67, 99, 50, 67, 99, 50, 71, 43, 57, 77, 88, 70, 43, 112, 119, 81, 70, 105, 117, 98, 80, 72, 50, 89, 55, 73, 98, 43, 47, 83, 43, 115, 81, 72, 54, 61, 107});
    private static final String zzZNk = zzZif.zzYi2().zzXfR(new byte[]{107, 51, 52, 105, 84, 53, 67, 54, 108, 52, 49, 116, 74, 50, 77, 53, 75, 98, 114, 102, 68, 65, 67, 66, 99, 90, 56, 69, 84, 79, 102, 101, 110, 100, 99, 103, 73, 57, 75, 68, 43, 103, 122, 108, 71, 67, 76, 89, 120, 117, 70, 74, 116, 68, 54, 49, 119, 97, 74, 104, 110, 70, 50, 65, 115, 51, 43, 88, 81, 107, 47, 52, 90, 101, 53, 81, 78, 112, 89, 65, 99, 106, 90, 43, 48, 74, 112, 43, 119, 87, 81, 118, 52, 82, 56, 104, 74, 71, 101, 51, 118, 87, 99, 101, 70, 100, 55, 115, 83, 75, 119, 87, 109, 78, 88, 70, 67, 90, 78, 83, 115, 43, 114, 98, 119, 120, 106, 69, 122, 122, 115, 110, 107, 49, 72, 73, 76, 117, 102, 78, 114, 53, 90, 43, 103, 97, 110, 103, 43, 115, 114, 56, 88, 113, 49, 82, 82, 57, 74, 83, 79, 66, 117, 99, 113, 70, 105, 86, 73, 112, 118, 72, 54, 53, 70, 108, 51, 53, 99, 72, 104, 43, 43, 120, 57, 121, 82, 85, 97, 73, 100, 97, 49, 79, 56, 87, 107, 100, 113, 84, 119, 103, 120, 69, 102, 121, 110, 106, 68, 98, 97, 79, 66, 67, 72, 69, 67, 48, 85, 113, 49, 76, 73, 89, 67, 73, 47, 83, 73, 119, 84, 85, 75, 69, 77, 104, 90, 48, 119, 108, 99, 69, 99, 73, 108, 72, 88, 56, 72, 84, 86, 76, 57, 120, 68, 54, 88, 77, 98, 52, 86, 98, 106, 97, 55, 106, 76, 56, 75, 52, 101, 122, 81, 118, 52, 99, 50, 52, 88, 68, 56, 50, 71, 75, 74, 68, 118, 84, 69, 101, 49, 66, 83, 112, 87, 75, 114, 115, 100, 48, 70, 52, 120, 84, 119, 55, 83, 75, 54, 51, 66, 82, 87, 110, 53, 118, 119, 108, 82, 115, 114, 69, 84, 116, 98, 90, 99, 53, 86, 105, 71, 73, 105, 49, 74, 73, 112, 114, 55, 56, 81, 86, 61, 61});
    private static final String zzZHf = zzZif.zzYi2().zzXfR(new byte[]{52, 120, 79, 65, 75, 70, 110, 112, 87, 66, 54, 103, 81, 99, 99, 84, 72, 116, 72, 51, 122, 57, 65, 85, 48, 88, 54, 101, 119, 121, 83, 107, 57, 106, 68, 100, 75, 77, 80, 100, 111, 77, 77, 116, 50, 108, 110, 65, 104, 66, 71, 107, 100, 66, 77, 49, 118, 50, 102, 51, 102, 57, 55, 108, 103, 120, 115, 108, 112, 67, 75, 43, 67, 100, 66, 72, 65, 82, 120, 80, 113, 68, 121, 88, 87, 48, 98, 67, 102, 80, 98, 83, 118, 114, 119, 43, 79, 104, 90, 56, 115, 70, 48, 84, 86, 65, 101, 108, 69, 88, 74, 77, 71, 53, 121, 109, 79, 122, 97, 98, 80, 81, 55, 72, 102, 111, 69, 102, 120, 73, 83, 120, 106, 119, 115, 88, 86, 109, 72, 57, 120, 52, 65, 86, 85, 90, 105, 67, 97, 71, 100, 109, 102, 56, 87, 69, 100, 115, 54, 43, 98, 75, 77, 109, 88, 98, 97, 68, 79, 110, 78, 111, 65, 79, 55, 108, 75, 72, 122, 74, 87, 105, 121, 120, 103, 108, 80, 43, 99, 105, 70, 81, 88, 56, 57, 50, 90, 100, 71, 114, 82, 52, 57, 113, 54, 72, 119, 69, 98, 56, 55, 103, 114, 102, 113, 98, 77, 99, 47, 104, 56, 43, 111, 75, 122, 122, 120, 53, 103, 90, 116, 99, 105, 69, 78, 81, 103, 89, 89, 118, 99, 54, 57, 99, 81, 78, 105, 97, 112, 52, 104, 122, 51, 102, 79, 52, 97, 78, 47, 108, 84, 68, 118, 55, 98, 111, 79, 69, 43, 78, 72, 72, 116, 79, 106, 54, 86, 122, 72, 77, 122, 108, 81, 115, 85, 51, 99, 100, 119, 105, 105, 122, 68, 70, 109, 57, 81, 55, 86, 100, 104, 104, 98, 98, 103, 83, 98, 75, 55, 53, 47, 86, 55, 77, 86, 90, 75, 48, 76, 98, 54, 49, 110, 56, 110, 97, 99, 105, 52, 51, 103, 86, 117, 101, 103, 78, 103, 54, 117, 105, 119, 69, 61, 61});

    zzwH() {
        this.zzX5u = zz6e.zzXaX;
        this.zzW0y = zzZif.zzYi2();
    }

    void zzFe(String var1, Class var2) throws Exception {
        if (!"".equals(var1)) {
            InputStream var3 = this.zznj(var1, var2);

            try {
                this.zzFe(var3);
            } finally {
                if (var3 != null) {
                    var3.close();
                }

            }
        } else {
            zzYpB = this;
        }

    }

    void zzFe(InputStream var1) throws Exception {
        if (var1 == null) {
            throw new NullPointerException(zzZif.zzYi2().zzXfR(new byte[]{116, 115, 101, 114, 109, 97}));
        } else if (!this.zznj(var1)) {
            throw new IllegalStateException(zzZif.zzYi2().zzXfR(new byte[]{110, 73, 97, 118, 105, 108, 32, 100, 105, 108, 101, 99, 115, 110, 32, 101, 105, 115, 110, 103, 116, 97, 114, 117, 46, 101, 80, 32, 101, 108, 115, 97, 32, 101, 97, 109, 101, 107, 115, 32, 114, 117, 32, 101, 104, 116, 32, 101, 105, 108, 101, 99, 115, 110, 32, 101, 105, 102, 101, 108, 119, 32, 115, 97, 110, 32, 116, 111, 109, 32, 100, 111, 102, 105, 101, 105, 46, 100}));
        } else {
            zzWb2 var2 = zzZBh;
            if (var2 == null) {
                var2 = zzAH(zzZif.zzYi2().zzXfR(new byte[]{115, 65, 111, 112, 101, 115, 76, 46, 99, 105, 110, 101, 101, 115, 66, 46, 97, 108, 107, 99, 105, 76, 116, 115}), (String)null);
                zzZBh = var2;
            }

            zzWb2 var3 = zzzH;
            if (var3 == null) {
                var3 = zzAH(zzZif.zzYi2().zzXfR(new byte[]{111, 67, 104, 110, 108, 111, 97, 100, 101, 116, 76, 46, 99, 105, 110, 101, 101, 115, 66, 46, 97, 108, 107, 99, 105, 76, 116, 115}), zzY8i);
                zzzH = var3;
            }

            if (zzXQH.zzWiS() > 0) {
                throw new IllegalStateException(zzZif.zzYi2().zzXfR(new byte[]{110, 73, 97, 118, 105, 108, 32, 100, 105, 108, 101, 99, 115, 110, 32, 101, 105, 115, 110, 103, 116, 97, 114, 117, 46, 101, 80, 32, 101, 108, 115, 97, 32, 101, 97, 109, 101, 107, 115, 32, 114, 117, 32, 101, 104, 116, 32, 101, 105, 108, 101, 99, 115, 110, 32, 101, 105, 102, 101, 108, 119, 32, 115, 97, 110, 32, 116, 111, 109, 32, 100, 111, 102, 105, 101, 105, 46, 100}));
            } else if (!var2.contains(this.zzWhl) && !var3.contains(this.zzWhl)) {
                boolean var4 = false;
                String[] var5 = this.zzYt8;
                int var6 = var5.length;
                int var7 = 0;

                while(var7 < var6) {
                    String var8 = var5[var7];
                    if (!var8.equals(zzZif.zzYi2().zzXfR(new byte[]{115, 65, 111, 112, 101, 115, 84, 46, 116, 111, 108, 97, 102, 32, 114, 111, 32}) + "Java") && !var8.equals(zzZif.zzYi2().zzXfR(new byte[]{111, 67, 104, 110, 108, 111, 97, 100, 101, 116, 84, 46, 116, 111, 108, 97, 102, 32, 114, 111, 32}) + "Java")) {
                        if ((var8.equals(zzZif.zzYi2().zzXfR(new byte[]{115, 65, 111, 112, 101, 115, 84, 46, 116, 111, 108, 97})) || var8.equals(zzZif.zzYi2().zzXfR(new byte[]{111, 67, 104, 110, 108, 111, 97, 100, 101, 116, 84, 46, 116, 111, 108, 97}))) && "Java".equals(zzZif.zzYi2().zzXfR(new byte[]{78, 46, 84, 69}))) {
                            var4 = true;
                            break;
                        }

                        if (!var8.equals(zzZif.zzYi2().zzXfR(new byte[]{115, 65, 111, 112, 101, 115, 84, 46, 116, 111, 108, 97, 80, 32, 111, 114, 117, 100, 116, 99, 70, 32, 109, 97, 108, 105, 121})) && !var8.equals(zzZif.zzYi2().zzXfR(new byte[]{111, 67, 104, 110, 108, 111, 97, 100, 101, 116, 84, 46, 116, 111, 108, 97, 80, 32, 111, 114, 117, 100, 116, 99, 70, 32, 109, 97, 108, 105, 121}))) {
                            if (var8.equals("Aspose.Words for Java")) {
                                var4 = true;
                                break;
                            }

                            String var9;
                            String var10;
                            if ("Java".equals(zzZif.zzYi2().zzXfR(new byte[]{78, 46, 84, 69}))) {
                                var9 = "Aspose.Words" + zzZif.zzYi2().zzXfR(new byte[]{102, 32, 114, 111, 32}) + zzZif.zzYi2().zzXfR(new byte[]{97, 88, 97, 109, 105, 114, 46, 110, 110, 65, 114, 100, 105, 111, 100});
                                var10 = zzZif.zzYi2().zzXfR(new byte[]{115, 65, 111, 112, 101, 115, 84, 46, 116, 111, 108, 97, 102, 32, 114, 111, 88, 32, 109, 97, 114, 97, 110, 105, 65, 46, 100, 110, 111, 114, 100, 105});
                                String var11 = "Aspose.Words" + zzZif.zzYi2().zzXfR(new byte[]{102, 32, 114, 111, 32}) + zzZif.zzYi2().zzXfR(new byte[]{110, 65, 114, 100, 105, 111, 32, 100, 105, 118, 32, 97, 97, 88, 97, 109, 105, 114, 110});
                                String var12 = zzZif.zzYi2().zzXfR(new byte[]{115, 65, 111, 112, 101, 115, 84, 46, 116, 111, 108, 97, 102, 32, 114, 111, 65, 32, 100, 110, 111, 114, 100, 105, 118, 32, 97, 105, 88, 32, 109, 97, 114, 97, 110, 105});
                                String var13 = "Aspose.Words" + zzZif.zzYi2().zzXfR(new byte[]{102, 32, 114, 111, 32}) + zzZif.zzYi2().zzXfR(new byte[]{79, 105, 32, 83, 105, 118, 32, 97, 97, 88, 97, 109, 105, 114, 110});
                                String var14 = zzZif.zzYi2().zzXfR(new byte[]{115, 65, 111, 112, 101, 115, 84, 46, 116, 111, 108, 97, 102, 32, 114, 111, 105, 32, 83, 79, 118, 32, 97, 105, 88, 32, 109, 97, 114, 97, 110, 105});
                                String var15 = "Aspose.Words" + zzZif.zzYi2().zzXfR(new byte[]{102, 32, 114, 111, 32}) + zzZif.zzYi2().zzXfR(new byte[]{97, 77, 32, 99, 105, 118, 32, 97, 97, 88, 97, 109, 105, 114, 110});
                                String var16 = zzZif.zzYi2().zzXfR(new byte[]{115, 65, 111, 112, 101, 115, 84, 46, 116, 111, 108, 97, 102, 32, 114, 111, 77, 32, 99, 97, 118, 32, 97, 105, 88, 32, 109, 97, 114, 97, 110, 105});
                                if (var8.equals(var9) || var8.equals(var10) || var8.equals(var11) || var8.equals(var12) || var8.equals(var13) || var8.equals(var14) || var8.equals(var15) || var8.equals(var16)) {
                                    var4 = true;
                                    break;
                                }
                            }

                            if ("Java".equals(zzZif.zzYi2().zzXfR(new byte[]{97, 74, 97, 118, 65, 46, 100, 110, 111, 114, 100, 105}))) {
                                var9 = "Aspose.Words" + zzZif.zzYi2().zzXfR(new byte[]{102, 32, 114, 111, 32}) + zzZif.zzYi2().zzXfR(new byte[]{110, 65, 114, 100, 105, 111, 100});
                                var10 = zzZif.zzYi2().zzXfR(new byte[]{115, 65, 111, 112, 101, 115, 84, 46, 116, 111, 108, 97, 102, 32, 114, 111, 65, 32, 100, 110, 111, 114, 100, 105});
                                if (var8.equals(var9) || var8.equals(var10)) {
                                    var4 = true;
                                    break;
                                }
                            }

                            if (var8.equals("Aspose.Words") && "Java".equals(zzZif.zzYi2().zzXfR(new byte[]{78, 46, 84, 69}))) {
                                var4 = true;
                                break;
                            }

                            if (var8.equals("Aspose.Words" + zzZif.zzYi2().zzXfR(new byte[]{80, 32, 111, 114, 117, 100, 116, 99, 70, 32, 109, 97, 108, 105, 121}))) {
                                var4 = true;
                                break;
                            }

                            ++var7;
                            continue;
                        }

                        var4 = true;
                        break;
                    }

                    var4 = true;
                    break;
                }

                if (!var4) {
                    throw new IllegalStateException(zzZif.zzYi2().zzXfR(new byte[]{104, 84, 32, 101, 105, 108, 101, 99, 115, 110, 32, 101, 115, 105, 110, 32, 116, 111, 118, 32, 108, 97, 100, 105, 102, 32, 114, 111, 116, 32, 105, 104, 32, 115, 114, 112, 100, 111, 99, 117, 46, 116}));
                } else {
                    Date var17 = (new SimpleDateFormat("yyyy.MM.dd")).parse("2021.10.01");
                    if (var17.after(this.zzZ2e)) {
                        throw new IllegalStateException(this.zzW0y.zzfT(new byte[]{84, 104, 101, 32, 115, 117, 98, 115, 99, 114, 105, 112, 116, 105, 111, 110, 32, 105, 110, 99, 108, 117, 100, 101, 100, 32, 105, 110, 32, 116, 104, 105, 115, 32, 108, 105, 99, 101, 110, 115, 101, 32, 97, 108, 108, 111, 119, 115, 32, 102, 114, 101, 101, 32, 117, 112, 103, 114, 97, 100, 101, 115, 32, 117, 110, 116, 105, 108, 32}) + (new SimpleDateFormat(this.zzW0y.zzfT(new byte[]{100, 100, 32, 77, 77, 77, 32, 121, 121, 121, 121}), Locale.ENGLISH)).format(this.zzZ2e) + this.zzW0y.zzfT(new byte[]{44, 32}) + this.zzW0y.zzfT(new byte[]{98, 117, 116, 32, 116, 104, 105, 115, 32, 118, 101, 114, 115, 105, 111, 110, 32, 111, 102, 32, 116, 104, 101, 32, 112, 114, 111, 100, 117, 99, 116, 32, 119, 97, 115, 32, 114, 101, 108, 101, 97, 115, 101, 100, 32, 111, 110, 32}) + (new SimpleDateFormat(this.zzW0y.zzfT(new byte[]{100, 100, 32, 77, 77, 77, 32, 121, 121, 121, 121}), Locale.ENGLISH)).format(var17) + this.zzW0y.zzfT(new byte[]{46, 32}) + this.zzW0y.zzfT(new byte[]{80, 108, 101, 97, 115, 101, 32, 114, 101, 110, 101, 119, 32, 116, 104, 101, 32, 115, 117, 98, 115, 99, 114, 105, 112, 116, 105, 111, 110, 32, 111, 114, 32, 117, 115, 101, 32, 97, 32, 112, 114, 101, 118, 105, 111, 117, 115, 32, 118, 101, 114, 115, 105, 111, 110, 32, 111, 102, 32, 116, 104, 101, 32, 112, 114, 111, 100, 117, 99, 116, 46}));
                    } else if ((new Date()).after(this.zzW5P)) {
                        throw new IllegalStateException(zzZif.zzYi2().zzXfR(new byte[]{104, 84, 32, 101, 105, 108, 101, 99, 115, 110, 32, 101, 97, 104, 32, 115, 120, 101, 105, 112, 101, 114, 46, 100}));
                    } else if (this.zzZ2e.getYear() < 2099) {
                        this.zzX5u = zz6e.zzZIu;
                        zzYpB = this;
                    }
                }
            } else {
                throw new IllegalStateException(zzZif.zzYi2().zzXfR(new byte[]{104, 84, 115, 105, 108, 32, 99, 105, 110, 101, 101, 115, 105, 32, 32, 115, 105, 100, 97, 115, 108, 98, 100, 101, 32, 44, 108, 112, 97, 101, 101, 115, 99, 32, 110, 111, 97, 116, 116, 99, 65, 32, 112, 115, 115, 111, 32, 101, 111, 116, 111, 32, 116, 98, 105, 97, 32, 110, 32, 97, 101, 110, 32, 119, 105, 108, 101, 99, 115, 110, 46, 101}));
            }
        }
    }

    static byte[] zznL() {
        boolean var0 = zzYpB == null || zzYpB.zzX5u == zz6e.zzXaX || (new Date()).after(zzYpB.zzW5P) || zzXQH.zzKW() == 4096;
        if (zzXQY == 0L) {
            zzXQY ^= zzXW5;
        }

        boolean var1 = false;
        if (zzYfM.zzWtF() != null) {
            var1 = zzYfM.zzsM() == zzWZf.zzXaX;
            byte[] var2 = var0 && var1 ? zz6e.zzXaX : zz6e.zzZIu;
            return var2;
        } else {
            return null;
        }
    }

    static byte[] zzZ5L() {
        boolean var0 = zzYpB == null || zzYpB.zzX5u == zz6e.zzXaX || (new Date()).after(zzYpB.zzW5P) || zzXQH.zzKW() == 4096;
        boolean var1 = zzYfM.zzsM() == zzWZf.zzXaX;
        byte[] var2 = var0 && var1 ? zz6e.zzXaX : zz6e.zzZIu;
        return var2;
    }

    private boolean zznj(InputStream var1) throws Exception {
        DocumentBuilderFactory var2 = zzW7f.zzWum();
        DocumentBuilder var3 = var2.newDocumentBuilder();
        Document var4 = var3.parse(var1);
        Element var5 = var4.getDocumentElement();
        Element var6 = zzVTQ(var5, zzZif.zzYi2().zzXfR(new byte[]{97, 68, 97, 116}));
        Element var7 = zzVTQ(var5, zzZif.zzYi2().zzXfR(new byte[]{105, 83, 110, 103, 116, 97, 114, 117, 101}));
        boolean var8 = zzZa5((Node)var6, (Node)var7);
        Element var9 = zzVTQ(var6, zzZif.zzYi2().zzXfR(new byte[]{114, 80, 100, 111, 99, 117, 115, 116}));
        NodeList var10 = var9.getElementsByTagName(zzZif.zzYi2().zzXfR(new byte[]{114, 80, 100, 111, 99, 117, 116}));
        this.zzYt8 = new String[var10.getLength()];

        for(int var11 = 0; var11 < this.zzYt8.length; ++var11) {
            this.zzYt8[var11] = var10.item(var11).getFirstChild().getNodeValue();
        }

        this.zzWhl = zzD8(var6, zzZif.zzYi2().zzXfR(new byte[]{101, 83, 105, 114, 108, 97, 117, 78, 98, 109, 114, 101}));
        this.zzZ2e = zzZa5(var6, zzZif.zzYi2().zzXfR(new byte[]{117, 83, 115, 98, 114, 99, 112, 105, 105, 116, 110, 111, 120, 69, 105, 112, 121, 114}));
        this.zzW5P = zzZa5(var6, zzZif.zzYi2().zzXfR(new byte[]{105, 76, 101, 99, 115, 110, 69, 101, 112, 120, 114, 105, 121}));
        return var8;
    }

    // private static boolean zzZa5(Node var0, Node var1) throws Exception {
    //     return zzWPL((Node)var0, (Node)var1, (String)null);
    // }
    private static boolean zzZa5(Node var0, Node var1) throws Exception {
        return true;
    }

    private static boolean zzWPL(Node var0, Node var1, String var2) throws Exception {
        byte[] var3;
        if (var0 != null) {
            StringBuilder var4 = new StringBuilder();
            zzWPL(var4, var0);
            var3 = var4.toString().getBytes("UTF-16LE");
        } else {
            var3 = new byte[0];
        }

        byte[] var6;
        if (var1 != null) {
            String var5 = var1.getFirstChild().getNodeValue();
            var6 = zzWCy.zzWhE(var5);
        } else {
            var6 = new byte[0];
        }

        if (var2 == null) {
            if (zzVR1(var0)) {
                var2 = var6.length == 128 ? zzY8i : zzZHf;
            } else {
                var2 = var6.length == 128 ? zzYUh : zzZNk;
            }
        }

        return zzWPL(var3, var6, var2);
    }

    private static boolean zzWPL(byte[] var0, byte[] var1, String var2) throws Exception {
        String var3 = zzZif.zzYi2().zzXfR(new byte[]{81, 65, 66, 65});
        byte[] var4 = zzWCy.zzWhE(var3);
        byte[] var5 = zzWCy.zzWhE(var2);
        return zzYGb.zzWPL(var5, var4, var0, var1);
    }

    private InputStream zznj(String var1, Class var2) throws Exception {
        File var3 = new File(var1);
        if (var3.exists()) {
            return new zz4W(var3);
        } else {
            String var4 = zzWYw.zzWGQ(this.getClass());
            var3 = new File(var4, var1);
            if (var3.exists()) {
                return new zz4W(var3);
            } else {
                var4 = zzWYw.zzWGQ(var2);
                var3 = new File(var4, var1);
                if (var3.exists()) {
                    return new zz4W(var3);
                } else {
                    throw new IllegalStateException(zzZif.zzYi2().zzXfR(new byte[]{97, 67, 110, 110, 116, 111, 102, 32, 110, 105, 32, 100, 105, 108, 101, 99, 115, 110, 32, 101, 123, 39, 125, 48, 46, 39}) + var1 + "'.");
                }
            }
        }
    }

    private static synchronized zzWb2<String> zzAH(String var0, String var1) throws Exception {
        InputStream var2 = zzZvP.zzZa5(var0 + zzoy.zzfT(new byte[]{46, 82, 101, 97, 108, 46, 120, 109, 108}), License.class);
        if (var2 == null) {
            throw new IllegalStateException(zzZif.zzYi2().zzXfR(new byte[]{97, 67, 110, 110, 116, 111, 102, 32, 110, 105, 32, 100, 101, 114, 111, 115, 114, 117, 101, 99, 32, 46, 108, 80, 97, 101, 101, 115, 114, 32, 112, 101, 114, 111, 32, 116, 104, 116, 115, 105, 101, 32, 114, 114, 114, 111, 116, 32, 32, 111, 115, 65, 111, 112, 101, 115, 46}));
        } else {
            try {
                DocumentBuilderFactory var3 = zzW7f.zzWum();
                DocumentBuilder var4 = var3.newDocumentBuilder();
                Document var5 = var4.parse(var2);
                Element var6 = var5.getDocumentElement();
                Element var7 = zzVTQ(var6, zzZif.zzYi2().zzXfR(new byte[]{97, 68, 97, 116}));
                Element var8 = zzVTQ(var6, zzZif.zzYi2().zzXfR(new byte[]{105, 83, 110, 103, 116, 97, 114, 117, 101}));
                if (!zzWPL((Node)var7, (Node)var8, var1)) {
                    zzZdf.zzWPL(false, "Incorrect signature.");
                }

                zzWb2 var9 = new zzWb2();
                NodeList var10 = var7.getElementsByTagName(zzoy.zzfT(new byte[]{83, 78}));

                for(int var11 = 0; var11 < var10.getLength(); ++var11) {
                    var9.add(var10.item(var11).getFirstChild().getNodeValue());
                }

                zzWb2 var15 = var9;
                return var15;
            } finally {
                var2.close();
            }
        }
    }

    private static String zzD8(Element var0, String var1) {
        Element var2 = zzVTQ(var0, var1);
        return var2 != null ? var2.getFirstChild().getNodeValue() : "";
    }

    private static Date zzZa5(Element var0, String var1) throws ParseException {
        String var2 = zzD8(var0, var1);
        return !"".equals(var2) ? (new SimpleDateFormat("yyyyMMdd")).parse(var2) : new Date(253402300799999L);
    }

    private static Element zzVTQ(Element var0, String var1) {
        NodeList var2 = var0.getElementsByTagName(var1);
        return var2.getLength() > 0 ? (Element)var2.item(0) : null;
    }

    private static void zzWPL(StringBuilder var0, Node var1) {
        if (var1.getNodeType() == 1) {
            var0.append('<');
            var0.append(var1.getNodeName());
            var0.append('>');
            NodeList var2 = var1.getChildNodes();
            if (zzWPL(var2)) {
                Node var5 = var2.item(0);
                String var4 = var5.getNodeValue();
                var4 = var4.replace("&", "&amp;");
                var4 = var4.replace("<", "&lt;");
                var4 = var4.replace(">", "&gt;");
                var0.append(var4);
            } else {
                for(int var3 = 0; var3 < var2.getLength(); ++var3) {
                    zzWPL(var0, var2.item(var3));
                }
            }

            var0.append('<');
            var0.append('/');
            var0.append(var1.getNodeName());
            var0.append('>');
        }

    }

    private static PublicKey zzad(String var0, String var1) throws Exception {
        byte[] var2 = zzWCy.zzWhE(var0);
        byte[] var3 = zzWCy.zzWhE(var1);
        var2 = zzW6(var2);
        var3 = zzW6(var3);
        BigInteger var4 = new BigInteger(1, var2);
        BigInteger var5 = new BigInteger(1, var3);
        RSAPublicKeySpec var6 = new RSAPublicKeySpec(var4, var5);
        return KeyFactory.getInstance("RSA").generatePublic(var6);
    }

    private static byte[] zzW6(byte[] var0) {
        int var2 = -1;

        for(int var3 = 0; var3 < var0.length && var0[var3] == 0; var2 = var3++) {
        }

        ++var2;
        byte[] var1;
        if (var2 > 0) {
            var1 = new byte[var0.length - var2];
            System.arraycopy(var0, var2, var1, 0, var1.length);
        } else {
            var1 = var0;
        }

        return var1;
    }

    private static boolean zzWPL(NodeList var0) {
        Node var1;
        return var0 != null && var0.getLength() == 1 && (var1 = var0.item(0)) != null && var1.getNodeType() == 3;
    }

    private static NodeList zzXWe(Node var0) {
        if (var0 == null) {
            return null;
        } else {
            Element var1 = (Element)var0;
            return var1.getElementsByTagName(zzZif.zzYi2().zzXfR(new byte[]{114, 80, 100, 111, 99, 117, 115, 116}));
        }
    }

    private static boolean zzVR1(Node var0) {
        NodeList var1 = zzXWe(var0);
        if (var1 == null) {
            return false;
        } else {
            for(int var2 = 0; var2 < var1.getLength(); ++var2) {
                Node var3 = var1.item(var2);
                String var4 = var3.getTextContent();
                if (zzXXi.zzXFy(var4) && zzXXi.zzVTQ(var4, zzZif.zzYi2().zzXfR(new byte[]{111, 67, 104, 110, 108, 111, 97, 100, 101, 116}), true)) {
                    return true;
                }
            }

            return false;
        }
    }
}
