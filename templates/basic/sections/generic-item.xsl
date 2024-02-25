<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="generic-item">
        <xsl:param name="value" select="'default-value'"/>
        <xsl:param name="title" select="'default-value'"/>
        <fo:block text-align="left" font-size="10pt">

            <fo:table font-size="8pt">
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-body>

                    <fo:table-row>
                        <fo:table-cell number-columns-spanned="3">
                            <fo:block text-align="left" font-weight="bold">
                                <xsl:value-of select="$title"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell number-columns-spanned="3">
                            <fo:block padding-top="4px">
                                <xsl:value-of select="$value"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>