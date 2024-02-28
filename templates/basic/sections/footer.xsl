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
                            <xsl:value-of select="$root/translateConsentProcessingData"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell>
                        <fo:block text-align="right">
                            <fo:page-number/>
                            <xsl:value-of select="' '"/>
                            <xsl:value-of select="$root/translateOf"/>
                            <xsl:value-of select="' '"/>
                            <fo:page-number-citation-last ref-id="end"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
        </fo:table>
    </xsl:template>
</xsl:stylesheet>