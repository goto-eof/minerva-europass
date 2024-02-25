package com.andreidodu.minervaeuropass.service.impl;


import com.andreidodu.minervaeuropass.constants.ResumeConst;
import com.andreidodu.minervaeuropass.exception.ApplicationException;
import com.andreidodu.minervaeuropass.service.XMLService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class XMLServiceImpl implements XMLService {


    @Override
    public InputStream createXMLFromMap(Map<String, Object> map) {
        try {
            return generateByteArrayStream(map);
        } catch (ParserConfigurationException | TransformerException pce) {
            log.error("Fatal error while trying to convert the map to xml: " + pce.getMessage());
            throw new ApplicationException("Unable to create XML");
        }
    }

    private ByteArrayInputStream generateByteArrayStream(Map<String, Object> map) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element root = createAndAppendNode(document, document, ResumeConst.FIELD_GLOBAL_ROOT);
        createNodesFromMapRecursively(map, root, document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StreamResult streamResult = new StreamResult(outputStream);
        transformer.transform(domSource, streamResult);
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    private void createNodesFromMapRecursively(Map<String, Object> map, Node root, Document document) {
        map.keySet().forEach(key -> {
            if (map.get(key) instanceof String) {
                Element element = createAndAppendNode(root, document, key);
                createAndAppendTextNode(map, document, key, element);
            } else if (map.get(key) instanceof List<?>) {
                Element items = createAndAppendNode(root, document, key);
                List<Map<String, Object>> itemsMap = (List<Map<String, Object>>) map.get(key);
                for (Map<String, Object> nestedMap : itemsMap) {
                    Element item = createAndAppendNode(items, document, ResumeConst.FIELD_GLOBAL_ITEM);
                    createNodesFromMapRecursively(nestedMap, item, document);
                }
            } else if (map.get(key) instanceof Map<?, ?>) {
                Element items = createAndAppendNode(root, document, key);
                Map<String, Object> itemsMap = (Map<String, Object>) map.get(key);

                for (String tag : itemsMap.keySet()) {
                    Element item = createAndAppendNode(items, document, tag);
                    createAndAppendTextNode(itemsMap, document, tag, item);
                }
            }
        });
    }

    private static void createAndAppendTextNode(Map<String, Object> map, Document document, String key, Element element) {
        Text text = document.createTextNode((String) map.get(key));
        element.appendChild(text);
    }

    private static Element createAndAppendNode(Node root, Document document, String key) {
        Element element = document.createElement(key);
        root.appendChild(element);
        return element;
    }
}


