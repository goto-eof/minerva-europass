<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="generic-item-same-row-bi-value">
        <xsl:param name="value1" select="'default-value'"/>
        <xsl:param name="value2" select="'default-value'"/>
        <xsl:param name="value2Label" select="'default-value'"/>
        <xsl:param name="title" select="'default-value'"/>
        <fo:block text-align="left" font-size="10pt">

            <fo:table font-size="8pt">
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-body>

                    <fo:table-row>
                        <fo:table-cell number-columns-spanned="3">
                            <fo:block>
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="$title"/>
                                </fo:inline>
                                <fo:inline>
                                    <xsl:value-of select="': '"/>
                                </fo:inline>
                                <fo:inline>
                                    <xsl:value-of select="$value1"/>
                                    <xsl:value-of select="' ('"/>
                                    <xsl:value-of select="$value2"/>
                                    <xsl:value-of select="' '"/>
                                    <xsl:value-of select="$value2Label"/>
                                    <xsl:value-of select="')'"/>
                                </fo:inline>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>