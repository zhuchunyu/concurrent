package com.habage.test;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * @author yuz
 */
public class Dom4j {
    public static void main(String[] args) throws Exception {
        System.out.println("dom4j");
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<LogonResponse xmlns=\"http://www.ibm.com/xmlns/systems/power/firmware/web/mc/2012_10/\"\n" +
                " schemaVersion=\"V1_1_0\">\n" +
                "    <Metadata>\n" +
                "        <Atom/>\n" +
                "    </Metadata>\n" +
                "    <X-API-Session kxe=\"false\" kb=\"ROR\">" +
                "gDHtF9lOmKjiHPkdaR0Bv4Ep1fXn8Qh4H8okf88pJgz9mVRT3hG_XD8Iu3d4fZ1KB95ytiNusdu" +
                "hpKSdMFXCBQgY4YcABiPyztI8ZZSTqk33bqDrDnmdrmJdyCXLWDYgFCIGs6Cba_fs7_83JvaVAb" +
                "V7zaTwmpiwfVD2r_8faiawZ2SiPNN9fDbvQHx0Z1S6KT92osIo4d09U2J1C4aJKtJWs65JcPkQD-wtunLa_j4=" +
                "</X-API-Session>\n" +
                "</LogonResponse>";

        Document doc = DocumentHelper.parseText(xml);
        System.out.println(doc.getDocType());

        Node node = doc.getRootElement().selectSingleNode("/LogonResponse/*[@kb]");
        System.out.println(node.getText());
        System.out.println(node.getNodeType());
        System.out.println("=============");
        System.out.println(doc.getRootElement().selectSingleNode("/LogonResponse"));

        //JSONObject json=xml2JSON(xml.getBytes());
        //System.out.println(json.toJSONString());
    }

    public static JSONObject xml2JSON(byte[] xml) throws JDOMException, IOException {
        JSONObject json = new JSONObject();
        InputStream is = new ByteArrayInputStream(xml);
        SAXBuilder sb = new SAXBuilder();
        org.jdom2.Document doc = sb.build(is);
        Element root = doc.getRootElement();
        json.put(root.getName(), iterateElement(root));
        return json;
    }

    private static JSONObject iterateElement(Element element) {
        List node = element.getChildren();
        Element et = null;
        JSONObject obj = new JSONObject();
        List list = null;
        for (int i = 0; i < node.size(); i++) {
            list = new LinkedList();
            et = (Element) node.get(i);
            if (et.getTextTrim().equals("")) {
                if (et.getChildren().size() == 0) {
                    continue;
                }
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(iterateElement(et));
                obj.put(et.getName(), list);
            } else {
                if (obj.containsKey(et.getName())) {
                    list = (List) obj.get(et.getName());
                }
                list.add(et.getTextTrim());
                obj.put(et.getName(), list);
            }
        }
        return obj;
    }
}
