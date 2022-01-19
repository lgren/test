package office.aspose;

import javassist.*;
import lombok.SneakyThrows;
import org.junit.Test;
import org.w3c.dom.Node;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class LicenseCrack {
    private CtClass addLicenseGo(CtClass licenseClazz, String licenseClassName, String signature) throws CannotCompileException {
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "licenseGo", new CtClass[]{}, licenseClazz);
        ctMethod.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
        //region body
        ctMethod.setBody(String.format("{" +
                "    String licenseStr = \"<License><Data><Products><Product>Aspose.Total for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry></Data><Signature>%2$s</Signature></License>\";\n" +
                "    try {\n" +
                "        new %1$s().setLicense(new java.io.ByteArrayInputStream(licenseStr.getBytes(java.nio.charset.StandardCharsets.UTF_8)));\n" +
                "    } catch (Exception ignored) { }" +
                "}", licenseClassName, signature));
        //endregion
        licenseClazz.addMethod(ctMethod);
        return licenseClazz;
    }

    @SneakyThrows
    @Test
    public void crackWords() {
        ClassPool.getDefault().insertClassPath("/Users/lgren/DevTool/Maven/Repository/com/aspose/aspose-words/21.10/aspose-words-21.10-jdk17.jar");
        String type = "words";
        String signature = Base64.getEncoder().encodeToString("lgren".getBytes(StandardCharsets.UTF_8));

        String licenseClassName = String.format("com.aspose.%s.License", type);
        CtClass licenseClazz = ClassPool.getDefault().getCtClass(licenseClassName);

        addLicenseGo(licenseClazz, licenseClassName, signature).writeFile(String.format("/Users/lgren/Project/Java/0My/test/lib/%s", type));

        CtClass clazz = ClassPool.getDefault().getCtClass(String.format("com.aspose.%s.%s", type, "zzwH"));
        clazz.getDeclaredMethod("zzZa5", new CtClass[]{
                        ClassPool.getDefault().get("org.w3c.dom.Node"),
                        ClassPool.getDefault().get("org.w3c.dom.Node")})
                .setBody("{return true;}");

        licenseClazz.writeFile(String.format("/Users/lgren/Project/Java/0My/test/lib/%s", type));
        if (licenseClazz != clazz) {
            clazz.writeFile(String.format("/Users/lgren/Project/Java/0My/test/lib/%s", type));
        }
    }

    @SneakyThrows
    @Test
    public void crackCells() {
        ClassPool.getDefault().insertClassPath("/Users/lgren/DevTool/Maven/Repository/com/aspose/aspose-cells/21.10/aspose-cells-21.10.jar");
        String type = "cells";
        String signature = Base64.getEncoder().encodeToString("lgren".getBytes(StandardCharsets.UTF_8));

        String licenseClassName = String.format("com.aspose.%s.License", type);
        CtClass licenseClazz = ClassPool.getDefault().getCtClass(licenseClassName);

        addLicenseGo(licenseClazz, licenseClassName, signature);

        CtClass clazz = licenseClazz;
        clazz.getDeclaredMethod("a", new CtClass[]{
                        ClassPool.getDefault().get("java.lang.String"),
                        ClassPool.getDefault().get("java.lang.String"),
                        CtClass.booleanType,
                        CtClass.booleanType})
                .setBody("{return true;}");

        licenseClazz.writeFile(String.format("/Users/lgren/Project/Java/0My/test/lib/%s", type));
        if (licenseClazz != clazz) {
            clazz.writeFile(String.format("/Users/lgren/Project/Java/0My/test/lib/%s", type));
        }
    }

    @SneakyThrows
    @Test
    public void crackSlides() {
        ClassPool.getDefault().insertClassPath("/Users/lgren/DevTool/Maven/Repository/com/aspose/aspose-slides/21.10/aspose-slides-21.10-jdk16.jar");
        String type = "slides";
        String signature = Base64.getEncoder().encodeToString("lgren".getBytes(StandardCharsets.UTF_8));

        String licenseClassName = String.format("com.aspose.%s.License", type);
        CtClass licenseClazz = ClassPool.getDefault().getCtClass(licenseClassName);

        addLicenseGo(licenseClazz, licenseClassName, signature);

        CtClass clazz = ClassPool.getDefault().getCtClass(String.format("com.aspose.%s.internal.of.public", type));
        clazz.getDeclaredMethod("do", new CtClass[]{
                        ClassPool.getDefault().get(Node.class.getName()),
                        ClassPool.getDefault().get(Node.class.getName()),
                        ClassPool.getDefault().get(String[].class.getName())})
                .setBody("{}");

        licenseClazz.writeFile(String.format("/Users/lgren/Project/Java/0My/test/lib/%s", type));
        if (licenseClazz != clazz) {
            clazz.writeFile(String.format("/Users/lgren/Project/Java/0My/test/lib/%s", type));
        }
    }

    @SneakyThrows
    @Test
    public void crackOcr() {
        ClassPool.getDefault().insertClassPath("/Users/lgren/DevTool/Maven/Repository/com/aspose/aspose-ocr/21.10/aspose-ocr-21.10.jar");

        String type = "ocr";
        String licenseStr = String.format("<License><Data><Products><Product>Aspose.Total for Java</Product></Products><EditionType>Enterprise</EditionType><SubscriptionExpiry>20991231</SubscriptionExpiry><LicenseExpiry>20991231</LicenseExpiry></Data><Signature>%s</Signature></License>", Base64.getEncoder().encodeToString("lgren".getBytes(StandardCharsets.UTF_8)));

        String licenseClassName = String.format("com.aspose.%s.License", type);
        CtClass licenseClazz = ClassPool.getDefault().getCtClass(licenseClassName);

        addOcrLicenseGo(licenseClazz, licenseClassName, licenseStr);

        CtClass clazz = licenseClazz;
        // f(String var0, byte[] var1, byte[] var2)
        clazz.getDeclaredMethod("f", new CtClass[]{
                        ClassPool.getDefault().get(String.class.getName()),
                        ClassPool.getDefault().get(byte[].class.getName()),
                        ClassPool.getDefault().get(byte[].class.getName())})
                .setBody("{return true;}");

        licenseClazz.writeFile(String.format("/Users/lgren/Project/Java/0My/test/lib/%s", type));
        if (licenseClazz != clazz) {
            clazz.writeFile(String.format("/Users/lgren/Project/Java/0My/test/lib/%s", type));
        }
    }

    private void addOcrLicenseGo(CtClass licenseClazz, String licenseClassName, String licenseStr) throws CannotCompileException {
        licenseClazz.getRefClasses().forEach(ClassPool.getDefault()::importPackage);
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "licenseGo", new CtClass[]{}, licenseClazz);
        ctMethod.setModifiers(Modifier.STATIC | Modifier.PUBLIC);
        //region body
        ctMethod.setBody(String.format("{" +
                "    if (f) {\n" +
                "        return;\n" +
                "    }\n" +
                "    synchronized(License.class) {\n" +
                "        f = false;\n" +
                "        String var2 = \"%2$s\";\n" +
                "        String var31 = g.f(var2, \"Data\", true);\n" +
                "        String var32 = g.f(var2, \"Signature\");\n" +
                "        byte[] var5 = Base64.getDecoder().decode(var32);\n" +
                "        ArrayList var6 = g.c0ad(var2, \"Product\");\n" +
                "        boolean var7 = false;\n" +
                "        String var8 = \"\";\n" +
                "        Iterator var9 = var6.iterator();\n" +
                "        while(var9.hasNext()) {\n" +
                "            String var10 = (String)var9.next();\n" +
                "            if (var10.toUpperCase().indexOf(\"CONHOLDATE\") != -1) {\n" +
                "                var7 = true;\n" +
                "                break;\n" +
                "            }\n" +
                "        }\n" +
                "        if (var7) {\n" +
                "            var8 = \"qKFqqhFovZvEYCHeD3N8Oy+AuxrOA8cVvIl4u4qIBMQlzejGyXkiTUjoryuzlhlS2bG80FGmFsH+wUKbYiEkW/4zseZCV/Ej/usbu6yHACQKO/SCy3pYw68Pcv901pvywUnoaYCI2ccCG29+XM+FwpFQuiPb2H7YbI/++SQs6Hk=\";\n" +
                "        } else {\n" +
                "            var8 = var5.length == 128 ? \"0nRuwNEddXwLfXB7pw66G71MS93gW8mNzJ7vuh3Sf4VAEOBfpxtHLCotymv1PoeukxYe31K441Ivq0Pkvx1yZZG4O1KCv3Omdbs7uqzUB4xXHlOub4VsTODzDJ5MWHqlRCB1HHcGjlyT2sVGiovLt0Grvqw5+QXBuinoBY0suX0=\" : \"3ki45T6C4lt12J5MbKfrADBCZcE8OTefdngc9IDKg+lzCGYLuxJFDt16awhJFnA23sX+kQ4/eZQ5pNAYjc+ZJ0+pWwvQR4h8GJ3eWvecdFs7KSWwNmFXZCSN+sbrxwEjzzns1kIHuLNf5r+Zaggns+8rqXR19RSJBOcuFqVipIHv56lF53Hc+hx+y9URIaadO1W8dkTqgwExyfjnbDOaCBEH0CqUL1YIICS/wIUTEKhM0ZlwEcIcHl8XTHLVx96DMX4bbVajj78L4KzevQc442DX28KGDJTveEB1pSKWsr0d4FTx7wKS36RBnWv5lwsRErtTZb5ciVIG1iIJrp87VQ==\";\n" +
                "        }\n" +
                "        byte[] var33 = Base64.getDecoder().decode(var8);\n" +
                "        boolean var34 = f(var31, var5, var33);\n" +
                "        String var11 = g.f(var2, \"EditionType\");\n" +
                "        if (!var11.equalsIgnoreCase(\"Enterprise\") && !var11.equalsIgnoreCase(\"Professional\")) {\n" +
                "            throw new IllegalArgumentException(\"Invalid edition type.\");\n" +
                "        } else {\n" +
                "            boolean var12 = false;\n" +
                "            Iterator var13 = var6.iterator();\n" +
                "            String var14;\n" +
                "            while(var13.hasNext()) {\n" +
                "                var14 = (String)var13.next();\n" +
                "                if (var14.equals(\"Aspose.Total\") || var14.equals(\"Aspose.Total for Java\") || var14.equals(\"Aspose.Total Product Family\") || var14.equals(\"Aspose.OCR for Java\") || var14.equals(\"Aspose.OCR Product Family\") || var14.equals(\"Conholdate.Total\") || var14.equals(\"Conholdate.Total for Java\") || var14.equals(\"Conholdate.Total Product Family\")) {\n" +
                "                    var12 = true;\n" +
                "                    break;\n" +
                "                }\n" +
                "            }\n" +
                "            if (!var12) {\n" +
                "                throw new IllegalArgumentException(\"The license is not valid for this product.\");\n" +
                "            } else {\n" +
                "                String var35 = g.f(var2, \"SerialNumber\");\n" +
                "                var14 = new String(e0cd0c6d66.ac8a);\n" +
                "                ArrayList var15 = g.c0ad(var14, \"SN\");\n" +
                "                if (var15.contains(var35)) {\n" +
                "                    throw new IllegalArgumentException(\"The license in blacklist.\");\n" +
                "                } else {\n" +
                "                    String var16 = g.f(var2, \"SubscriptionExpiry\");\n" +
                "                    String var17 = g.f(var2, \"LicenseExpiry\");\n" +
                "                    if ((var16 + var17).isEmpty()) {\n" +
                "                        throw new IllegalArgumentException(\"Expiry termination license invalid.\");\n" +
                "                    } else {\n" +
                "                        String var18 = var17.compareToIgnoreCase(var16) == 1 ? var17 : var16;\n" +
                "                        DateTimeFormatter var19 = DateTimeFormatter.ofPattern(\"yyyyMMdd\");\n" +
                "                        LocalDateTime var20 = LocalDateTime.now();\n" +
                "                        var19.format(var20);\n" +
                "                        SimpleDateFormat var22 = new SimpleDateFormat(\"yyyyMMdd\", Locale.ENGLISH);\n" +
                "                        Date var23 = null;\n" +
                "                        try {\n" +
                "                            var23 = var22.parse(var16);\n" +
                "                        } catch (ParseException var28) {\n" +
                "                            var28.printStackTrace();\n" +
                "                        }\n" +
                "                        Date var24 = null;\n" +
                "                        try {\n" +
                "                            var24 = (new SimpleDateFormat(\"yyyy.MM.dd\", Locale.ENGLISH)).parse(\"2021.10.21\");\n" +
                "                        } catch (ParseException var27) {\n" +
                "                            var27.printStackTrace();\n" +
                "                        }\n" +
                "                        if (var24.after(var23)) {\n" +
                "                            throw new IllegalStateException(new String(new byte[]{84, 104, 101, 32, 115, 117, 98, 115, 99, 114, 105, 112, 116, 105, 111, 110, 32, 105, 110, 99, 108, 117, 100, 101, 100, 32, 105, 110, 32, 116, 104, 105, 115, 32, 108, 105, 99, 101, 110, 115, 101, 32, 97, 108, 108, 111, 119, 115, 32, 102, 114, 101, 101, 32, 117, 112, 103, 114, 97, 100, 101, 115, 32, 117, 110, 116, 105, 108, 32}) + (new SimpleDateFormat(\"dd MMM yyyy\", Locale.ENGLISH)).format(var23) + \", \" + new String(new byte[]{98, 117, 116, 32, 116, 104, 105, 115, 32, 118, 101, 114, 115, 105, 111, 110, 32, 111, 102, 32, 116, 104, 101, 32, 112, 114, 111, 100, 117, 99, 116, 32, 119, 97, 115, 32, 114, 101, 108, 101, 97, 115, 101, 100, 32, 111, 110, 32}) + (new SimpleDateFormat(\"dd MMM yyyy\", Locale.ENGLISH)).format(var24) + \". \" + new String(new byte[]{80, 108, 101, 97, 115, 101, 32, 114, 101, 110, 101, 119, 32, 116, 104, 101, 32, 115, 117, 98, 115, 99, 114, 105, 112, 116, 105, 111, 110, 32, 111, 114, 32, 117, 115, 101, 32, 97, 32, 112, 114, 101, 118, 105, 111, 117, 115, 32, 118, 101, 114, 115, 105, 111, 110, 32, 111, 102, 32, 116, 104, 101, 32, 112, 114, 111, 100, 117, 99, 116, 46}));\n" +
                "                        } else {\n" +
                "                            if (var34) {\n" +
                "                                f = true;\n" +
                "                            }\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}", licenseClassName, licenseStr));
        //endregion
        licenseClazz.addMethod(ctMethod);
    }

    @SneakyThrows
    @Test
    public void word() {
        ClassPool.getDefault().insertClassPath("/Users/lgren/DevTool/Maven/Repository/com/aspose/aspose-words/21.10/aspose-words-21.10-jdk17.jar");
        CtClass clazz = ClassPool.getDefault().getCtClass("com.aspose.words.zzwH");
        CtMethod method = clazz.getDeclaredMethod("zzZa5", new CtClass[]{ClassPool.getDefault().get("org.w3c.dom.Node")});
        method.setBody("{return true;}");
        clazz.writeFile("/Users/lgren/Project/Java/0My/test/lib");
    }

    @SneakyThrows
    @Test
    public void cells() {
        ClassPool.getDefault().insertClassPath("/Users/lgren/DevTool/Maven/Repository/com/aspose/aspose-cells/21.10/aspose-cells-21.10.jar");
        CtClass clazz = ClassPool.getDefault().getCtClass("com.aspose.cells.License");
        CtMethod method = clazz.getDeclaredMethod("a", new CtClass[]{
                ClassPool.getDefault().get("java.lang.String")
                , ClassPool.getDefault().get("java.lang.String")
                , CtClass.booleanType
                , CtClass.booleanType});
        method.setBody("{return true;}");
        clazz.writeFile("/Users/lgren/Project/Java/0My/test/lib");
    }


    @SneakyThrows
    @Test
    public void slides() {

        ClassPool.getDefault().insertClassPath("/Users/lgren/DevTool/Maven/Repository/com/aspose/aspose-slides/21.10/aspose-slides-21.10-jdk16.jar");
        CtClass clazz = ClassPool.getDefault().getCtClass("com.aspose.slides.internal.of.public");
        // do(Node var0, Node var1, String[] var2)
        CtMethod method = clazz.getDeclaredMethod("do", new CtClass[]{ClassPool.getDefault().get(Node.class.getName()), ClassPool.getDefault().get(Node.class.getName()), ClassPool.getDefault().get(String[].class.getName())});
        System.out.println();
        method.setBody("{}");
        clazz.writeFile("/Users/lgren/Project/Java/0My/test/lib");
    }


}
