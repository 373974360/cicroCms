/*     */ package com.cicro.util.xml;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringReader;
/*     */ import java.io.StringWriter;
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.apache.crimson.jaxp.DocumentBuilderFactoryImpl;
/*     */ import org.apache.xpath.XPathAPI;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ import org.xml.sax.InputSource;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class XmlManager
/*     */ {
/*  47 */   private static DocumentBuilderFactory dbf = new DocumentBuilderFactoryImpl();
/*     */ 
/*  48 */   static { dbf.setCoalescing(true);
/*  49 */     dbf.setIgnoringElementContentWhitespace(true);
/*  50 */     dbf.setIgnoringComments(true);
/*     */   }
/*     */ 
/*     */   public static Document createDOM()
/*     */     throws ParserConfigurationException
/*     */   {
/*     */     try
/*     */     {
/*  62 */       return dbf.newDocumentBuilder().newDocument();
/*     */     } catch (ParserConfigurationException ex) {
/*  64 */       ex.printStackTrace(System.out);
/*  65 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Document createDOM(String strFileName)
/*     */     throws ParserConfigurationException, SAXException, IOException
/*     */   {
/*  81 */     File fileObj = new File(strFileName);
/*  82 */     return createDOMByFile(fileObj);
/*     */   }
/*     */ 
/*     */   public static Document createDOMByFile(String strFile)
/*     */     throws ParserConfigurationException, SAXException, IOException
/*     */   {
/*  97 */     File file = new File(strFile);
/*  98 */     return createDOMByFile(file);
/*     */   }
/*     */ 
/*     */   public static Document createDOMByFile(File file)
/*     */     throws ParserConfigurationException, SAXException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 115 */       return dbf.newDocumentBuilder().parse(file);
/*     */     } catch (ParserConfigurationException ex) {
/* 117 */       ex.printStackTrace(System.out);
/* 118 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Document createDomByInputStream(InputStream is)
/*     */     throws IOException, SAXException
/*     */   {
/*     */     try
/*     */     {
/* 134 */       return dbf.newDocumentBuilder().parse(is);
/*     */     } catch (ParserConfigurationException ex) {
/* 136 */       ex.printStackTrace(System.out);
/* 137 */     }return null;
/*     */   }
/*     */ 
/*     */   public static Document createDOMByString(String strDOMContent)
/*     */     throws ParserConfigurationException, SAXException, IOException
/*     */   {
/*     */     try
/*     */     {
/* 154 */       return dbf.newDocumentBuilder().parse(
/* 155 */         new InputSource(new StringReader(strDOMContent)));
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 159 */       ex.printStackTrace(System.out);
/* 160 */     }return null;
/*     */   }
/*     */ 
/*     */   public static NodeList createNodeList(String strXML)
/*     */     throws ParserConfigurationException, SAXException, IOException, TransformerException
/*     */   {
/* 178 */     strXML = "<xml>\n" + strXML + "\n</xml>";
/* 179 */     Document doc = createDOMByString(strXML);
/* 180 */     NodeList nodelist = null;
/* 181 */     nodelist = XPathAPI.selectNodeList(doc, "/xml/*");
/* 182 */     return nodelist;
/*     */   }
/*     */ 
/*     */   public static Node createNode(String strXML)
/*     */     throws ParserConfigurationException, SAXException, IOException, TransformerException
/*     */   {
/*     */     try
/*     */     {
/* 200 */       NodeList nodelist = createNodeList(strXML);
/* 201 */       Node node = null;
/* 202 */       if ((nodelist != null) && (nodelist.getLength() > 0));
/* 203 */       return nodelist.item(0);
/*     */     }
/*     */     catch (Exception ex)
/*     */     {
/* 208 */       ex.printStackTrace(System.out);
/* 209 */     }return null;
/*     */   }
/*     */ 
/*     */   public static String[] doXMLQueryString(String strXPath, Node node)
/*     */     throws TransformerConfigurationException, TransformerException
/*     */   {
/* 226 */     Vector vect = new Vector();
/* 227 */     Transformer serializer = TransformerFactory.newInstance()
/* 228 */       .newTransformer();
/* 229 */     serializer.setOutputProperty("omit-xml-declaration", "yes");
/* 230 */     NodeIterator nl = XPathAPI.selectNodeIterator(node, strXPath);
/*     */     Node n;
/* 232 */     while ((n = nl.nextNode()) != null)
/*     */     {
/*     */       Node n;
/* 233 */       StringWriter strWr = new StringWriter();
/* 234 */       serializer.transform(new DOMSource(n), new StreamResult(strWr));
/* 235 */       vect.add(strWr.toString());
/*     */     }
/* 237 */     return (String[])vect.toArray(new String[vect.size()]);
/*     */   }
/*     */ 
/*     */   public static String node2String(Node node)
/*     */     throws TransformerConfigurationException, TransformerException
/*     */   {
/* 251 */     if (node == null) {
/* 252 */       throw new IllegalArgumentException("Node cannot be null");
/*     */     }
/* 254 */     String strReturn = null;
/*     */     try {
/* 256 */       if ((node instanceof Document))
/* 257 */         node = ((Document)node).getDocumentElement();
/* 258 */       StringWriter strWr = new StringWriter();
/* 259 */       Transformer serializer = TransformerFactory.newInstance()
/* 260 */         .newTransformer();
/* 261 */       serializer.setOutputProperty("indent", "yes");
/* 262 */       serializer.transform(new DOMSource(node), new StreamResult(strWr));
/* 263 */       strReturn = strWr.toString();
/* 264 */       return strReturn.replaceFirst("\\<\\?[ ]*xml.*\\?\\>", "");
/*     */     } catch (Exception e) {
/* 266 */       e.printStackTrace(System.out);
/* 267 */     }return strReturn;
/*     */   }
/*     */ 
/*     */   public static String node2String(Node node, String encode)
/*     */     throws TransformerConfigurationException, TransformerException
/*     */   {
/* 285 */     String strReturn = null;
/* 286 */     if ((node instanceof Document))
/* 287 */       node = ((Document)node).getDocumentElement();
/* 288 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 289 */     Transformer serializer = TransformerFactory.newInstance()
/* 290 */       .newTransformer();
/* 291 */     serializer.setOutputProperty("indent", "yes");
/* 292 */     serializer.setOutputProperty("encoding", encode);
/* 293 */     serializer.transform(new DOMSource(node), new StreamResult(out));
/*     */ 
/* 295 */     strReturn = out.toString();
/* 296 */     return strReturn;
/*     */   }
/*     */ 
/*     */   public static String node2StringParseByByte(Node node)
/*     */     throws TransformerConfigurationException, TransformerException
/*     */   {
/* 310 */     if (node == null) {
/* 311 */       throw new IllegalArgumentException("Node cannot be null");
/*     */     }
/* 313 */     String strReturn = null;
/* 314 */     if ((node instanceof Document))
/* 315 */       node = ((Document)node).getDocumentElement();
/* 316 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 317 */     Transformer serializer = TransformerFactory.newInstance()
/* 318 */       .newTransformer();
/* 319 */     serializer.setOutputProperty("indent", "yes");
/* 320 */     serializer.setOutputProperty("encoding", "ISO-8859-1");
/* 321 */     serializer.transform(new DOMSource(node), new StreamResult(out));
/* 322 */     strReturn = out.toString();
/* 323 */     return strReturn.replaceFirst("\\<\\?[ ]*xml.*\\?\\>", "");
/*     */   }
/*     */ 
/*     */   public static void node2File(Node node, String strFileName, String strEnCoding)
/*     */     throws TransformerConfigurationException, TransformerException
/*     */   {
/* 342 */     if (node == null) {
/* 343 */       return;
/*     */     }
/* 345 */     if ((strEnCoding == null) || (strEnCoding.trim().length() == 0)) {
/* 346 */       strEnCoding = "utf-8";
/*     */     }
/* 348 */     File outFile = new File(strFileName);
/*     */ 
/* 350 */     Transformer serializer = TransformerFactory.newInstance()
/* 351 */       .newTransformer();
/* 352 */     serializer.setOutputProperty("indent", "yes");
/* 353 */     serializer.setOutputProperty("encoding", strEnCoding);
/* 354 */     serializer.transform(new DOMSource(node), new StreamResult(outFile));
/*     */   }
/*     */ 
/*     */   public static String serializeNode(Node node)
/*     */     throws TransformerConfigurationException, TransformerException
/*     */   {
/* 368 */     if (node == null) {
/* 369 */       return null;
/*     */     }
/* 371 */     if ((node instanceof Document))
/* 372 */       node = ((Document)node).getDocumentElement();
/* 373 */     Transformer transformer = TransformerFactory.newInstance()
/* 374 */       .newTransformer();
/* 375 */     StringWriter sw = new StringWriter();
/* 376 */     transformer.transform(new DOMSource(node), new StreamResult(sw));
/* 377 */     return sw.toString();
/*     */   }
/*     */ 
/*     */   public static String serializeNode(Node node, Properties property)
/*     */     throws TransformerConfigurationException, TransformerException
/*     */   {
/* 401 */     if (node == null) {
/* 402 */       return null;
/*     */     }
/* 404 */     if ((node instanceof Document))
/* 405 */       node = ((Document)node).getDocumentElement();
/* 406 */     Transformer transformer = TransformerFactory.newInstance()
/* 407 */       .newTransformer();
/*     */ 
/* 409 */     transformer.setOutputProperties(property);
/* 410 */     StringWriter sw = new StringWriter();
/* 411 */     transformer.transform(new DOMSource(node), new StreamResult(sw));
/* 412 */     return sw.toString();
/*     */   }
/*     */ 
/*     */   public static NodeList queryNodeList(Node node, String strXPath)
/*     */     throws TransformerException
/*     */   {
/* 427 */     if (node == null) {
/* 428 */       return null;
/*     */     }
/* 430 */     NodeList nl = null;
/* 431 */     nl = XPathAPI.selectNodeList(node, strXPath);
/* 432 */     return nl;
/*     */   }
/*     */ 
/*     */   public static Node queryNode(Node node, String strXPath)
/*     */     throws TransformerException
/*     */   {
/* 447 */     if (node == null) {
/* 448 */       return null;
/*     */     }
/* 450 */     NodeList nl = queryNodeList(node, strXPath);
/* 451 */     Node n = null;
/* 452 */     if ((nl != null) && (nl.getLength() > 0)) {
/* 453 */       n = nl.item(0);
/*     */     }
/* 455 */     return n;
/*     */   }
/*     */ 
/*     */   public static String queryNodeValue(Node node, String strXPath)
/*     */     throws TransformerException
/*     */   {
/* 470 */     if (node == null) {
/* 471 */       return null;
/*     */     }
/* 473 */     Node n = queryNode(node, strXPath);
/* 474 */     String strR = null;
/* 475 */     if (n != null) {
/* 476 */       strR = queryNodeValue(n);
/*     */     }
/* 478 */     return strR;
/*     */   }
/*     */ 
/*     */   public static String queryNodeValue(Node node)
/*     */     throws TransformerException
/*     */   {
/* 490 */     String strR = null;
/* 491 */     if (node != null) {
/* 492 */       if ((node.getFirstChild() != null) && 
/* 493 */         (node.getNodeType() == 1))
/* 494 */         strR = node.getFirstChild().getNodeValue();
/* 495 */       else if (node.getNodeType() == 2) {
/* 496 */         strR = node.getNodeValue();
/*     */       }
/*     */     }
/* 499 */     return strR;
/*     */   }
/*     */ 
/*     */   public static String[] queryNodeValues(Node node, String strXPath)
/*     */     throws TransformerException
/*     */   {
/* 514 */     if (node == null) {
/* 515 */       return null;
/*     */     }
/* 517 */     NodeList nl = queryNodeList(node, strXPath);
/* 518 */     int iLength = nl.getLength();
/* 519 */     String[] strArr = new String[iLength];
/* 520 */     for (int i = 0; i < nl.getLength(); i++) {
/* 521 */       strArr[i] = queryNodeValue(nl.item(i));
/*     */     }
/* 523 */     return strArr;
/*     */   }
/*     */ 
/*     */   public static boolean insertNode(Node parentNode, Node childNode)
/*     */   {
/* 536 */     if (parentNode == null) {
/* 537 */       throw new IllegalArgumentException("The parentnode is null!");
/*     */     }
/* 539 */     if (childNode == null) {
/* 540 */       throw new IllegalArgumentException("The childnode is null!");
/*     */     }
/* 542 */     Document dom = null;
/* 543 */     if ((parentNode instanceof Document)) {
/* 544 */       dom = (Document)parentNode;
/* 545 */       parentNode = dom.getDocumentElement();
/*     */     } else {
/* 547 */       dom = parentNode.getOwnerDocument();
/*     */     }
/* 549 */     Node node = childNode.cloneNode(true);
/* 550 */     node = dom.importNode(node, true);
/* 551 */     parentNode.appendChild(node);
/* 552 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean insertNode(Node parentnode, String nodexml)
/*     */     throws ParserConfigurationException, TransformerException, IOException, SAXException
/*     */   {
/* 571 */     if (parentnode == null) {
/* 572 */       throw new IllegalArgumentException("The parentnode is null!");
/*     */     }
/* 574 */     Node childnode = null;
/* 575 */     childnode = createNode(nodexml);
/* 576 */     return insertNode(parentnode, childnode);
/*     */   }
/*     */ 
/*     */   public static boolean insertNodeBefore(Node parentnode, Node newChild, Node refChild)
/*     */   {
/* 592 */     if (parentnode == null) {
/* 593 */       throw new IllegalArgumentException("The parentnode is null!");
/*     */     }
/* 595 */     if (newChild == null) {
/* 596 */       throw new IllegalArgumentException("The new child node is null!");
/*     */     }
/*     */ 
/* 599 */     if (refChild == null) {
/* 600 */       insertNode(parentnode, newChild);
/*     */     }
/*     */ 
/* 603 */     Document dom = null;
/* 604 */     if ((parentnode instanceof Document)) {
/* 605 */       dom = (Document)parentnode;
/* 606 */       parentnode = dom.getDocumentElement();
/*     */     } else {
/* 608 */       dom = parentnode.getOwnerDocument();
/*     */     }
/*     */ 
/* 611 */     newChild = dom.importNode(newChild, true);
/*     */ 
/* 613 */     parentnode.insertBefore(newChild, refChild);
/* 614 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean insertNodeFirst(Node parentnode, Node newChild)
/*     */   {
/* 629 */     if (parentnode == null) {
/* 630 */       throw new IllegalArgumentException("The parentnode is null!");
/*     */     }
/* 632 */     if (newChild == null) {
/* 633 */       throw new IllegalArgumentException("The new child node is null!");
/*     */     }
/*     */ 
/* 636 */     Document dom = null;
/* 637 */     if ((parentnode instanceof Document)) {
/* 638 */       dom = (Document)parentnode;
/* 639 */       parentnode = dom.getDocumentElement();
/*     */     } else {
/* 641 */       dom = parentnode.getOwnerDocument();
/*     */     }
/*     */ 
/* 644 */     NodeList nl = parentnode.getChildNodes();
/* 645 */     if ((nl != null) && (nl.getLength() > 0)) {
/* 646 */       Node node = nl.item(0);
/* 647 */       insertNodeBefore(parentnode, newChild, node);
/*     */     } else {
/* 649 */       insertNode(parentnode, newChild);
/*     */     }
/* 651 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean replaceNode(Node parentNode, Node newNode, Node oldNode)
/*     */   {
/* 666 */     if (parentNode == null) {
/* 667 */       throw new IllegalArgumentException("The parent node is null!");
/*     */     }
/* 669 */     if (newNode == null) {
/* 670 */       throw new IllegalArgumentException("The new node is null!");
/*     */     }
/* 672 */     if (oldNode == null) {
/* 673 */       throw new IllegalArgumentException("The old node is null!");
/*     */     }
/* 675 */     Document dom = null;
/* 676 */     if ((parentNode instanceof Document)) {
/* 677 */       dom = (Document)parentNode;
/* 678 */       parentNode = dom.getDocumentElement();
/*     */     } else {
/* 680 */       dom = parentNode.getOwnerDocument();
/*     */     }
/* 682 */     newNode = newNode.cloneNode(true);
/* 683 */     newNode = dom.importNode(newNode, true);
/* 684 */     parentNode.replaceChild(newNode, oldNode);
/* 685 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean replaceNode(Node parentNode, String newxml, Node oldNode)
/*     */     throws ParserConfigurationException, TransformerException, IOException, SAXException
/*     */   {
/* 701 */     if (parentNode == null) {
/* 702 */       throw new IllegalArgumentException("The parent node is null!");
/*     */     }
/* 704 */     if (newxml == null) {
/* 705 */       throw new IllegalArgumentException("The new xml is null!");
/*     */     }
/* 707 */     if (oldNode == null) {
/* 708 */       throw new IllegalArgumentException("The old node is null!");
/*     */     }
/* 710 */     Document dom = null;
/* 711 */     if ((parentNode instanceof Document)) {
/* 712 */       dom = (Document)parentNode;
/* 713 */       parentNode = dom.getDocumentElement();
/*     */     } else {
/* 715 */       dom = parentNode.getOwnerDocument();
/*     */     }
/* 717 */     Node newNode = createNode(newxml);
/* 718 */     Node node = newNode.cloneNode(true);
/* 719 */     node = dom.importNode(node, true);
/* 720 */     parentNode.replaceChild(newNode, oldNode);
/* 721 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean removeNode(Node node)
/*     */   {
/* 735 */     if (node == null) {
/* 736 */       throw new IllegalArgumentException(
/* 737 */         "The remove node can not is null!");
/*     */     }
/*     */ 
/* 741 */     if (node.getParentNode() == null) {
/* 742 */       throw new IllegalArgumentException(
/* 743 */         "The remove node is root node , can not remove");
/*     */     }
/*     */ 
/* 747 */     node = node.getParentNode().removeChild(node);
/*     */ 
/* 749 */     return true;
/*     */   }
/*     */ 
/*     */   public static Node createNodeFromArray(String[][] stra, String[] straCol, String docName, String rootName, String rowName, String colName, String colNum)
/*     */   {
/* 781 */     if (stra == null) {
/* 782 */       return null;
/*     */     }
/* 784 */     int intColNum = Integer.parseInt(colNum);
/*     */ 
/* 786 */     int intRowCount = stra.length;
/* 787 */     int intRecordColNum = straCol.length;
/*     */ 
/* 789 */     int intRow = intRowCount / intColNum;
/* 790 */     if (intRowCount % intColNum != 0) {
/* 791 */       intRow++;
/*     */     }
/* 793 */     StringBuffer strBuffer = new StringBuffer();
/* 794 */     strBuffer.append("<");
/* 795 */     strBuffer.append(docName);
/* 796 */     strBuffer.append(">");
/* 797 */     strBuffer.append("<");
/* 798 */     strBuffer.append(rootName);
/* 799 */     strBuffer.append(">");
/*     */ 
/* 801 */     for (int i = 0; i < intRow; i++) {
/* 802 */       strBuffer.append("<");
/* 803 */       strBuffer.append(rowName);
/* 804 */       strBuffer.append(">");
/* 805 */       for (int ii = i * intColNum; ii < (i + 1) * intColNum; ii++) {
/* 806 */         strBuffer.append("<");
/* 807 */         strBuffer.append(colName);
/* 808 */         strBuffer.append(">");
/*     */ 
/* 810 */         for (int j = 0; j < intRecordColNum; j++) {
/*     */           String strTemp;
/*     */           try {
/* 813 */             strTemp = stra[ii][j];
/*     */           }
/*     */           catch (Exception strE)
/*     */           {
/*     */             String strTemp;
/* 815 */             strTemp = "";
/*     */           }
/* 817 */           if (strTemp == null) {
/* 818 */             strTemp = "";
/*     */           }
/*     */ 
/* 823 */           strBuffer.append("<");
/* 824 */           strBuffer.append(straCol[j]);
/* 825 */           strBuffer.append(">");
/* 826 */           strBuffer.append(strTemp);
/* 827 */           strBuffer.append("</");
/* 828 */           strBuffer.append(straCol[j]);
/* 829 */           strBuffer.append(">");
/*     */         }
/* 831 */         strBuffer.append("</");
/* 832 */         strBuffer.append(colName);
/* 833 */         strBuffer.append(">");
/*     */       }
/* 835 */       strBuffer.append("</");
/* 836 */       strBuffer.append(rowName);
/* 837 */       strBuffer.append(">");
/*     */     }
/* 839 */     strBuffer.append("</");
/* 840 */     strBuffer.append(rootName);
/* 841 */     strBuffer.append(">");
/* 842 */     strBuffer.append("</");
/* 843 */     strBuffer.append(docName);
/* 844 */     strBuffer.append(">");
/*     */     try
/*     */     {
/* 847 */       return createDOMByString(strBuffer.toString()); } catch (Exception e) {
/*     */     }
/* 849 */     return null;
/*     */   }
/*     */ 
/*     */   public static void main(String[] arg)
/*     */   {
/*     */     try
/*     */     {
/* 865 */       String xml = "<cicroxml><infoTree infoId=\"12856\" treeId=\"5267\" flag=\"0\" id=\"12857\" time=\"2009-11-24 09:44:13\"/><infoTree infoId=\"12856\" treeId=\"1083\" flag=\"1\" id=\"12858\" time=\"2009-11-24 09:45:30\"/></cicroxml>";
/* 866 */       Node node = createNode(xml);
/* 867 */       NodeList infoNode = queryNodeList(node, "//infoTree");
/* 868 */       for (int i = 0; i < infoNode.getLength(); i++)
/*     */       {
/* 871 */         System.out.println(infoNode.item(i).toString());
/*     */       }
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Administrator\Desktop\wcm\shared\classes.zip
 * Qualified Name:     classes.com.cicro.util.xml.XmlManager
 * JD-Core Version:    0.6.2
 */