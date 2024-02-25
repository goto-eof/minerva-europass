<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="footer">
        <xsl:param name="root" select="'default-value'"/>

        <fo:table font-size="7pt" space-before="0.5em" space-after="0.5em" color="gray">

            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>

            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell number-columns-spanned="6" padding="2px">
                        <fo:block font-size="5pt">
                            Autorizzo il trattamento dei miei dati personali presenti nel CV ai sensi dell’art. 13 d.
                            lgs. 30 giugno 2003 n. 196 - “Codice in materia di protezione dei dati personali” e
                            dell’art. 13 GDPR 679/16 - “Regolamento europeo sulla protezione dei dati personali”.
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block text-align="right">
                            <fo:page-number/>/
                            <fo:page-number-citation-last ref-id="end"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>
</xsl:stylesheet>