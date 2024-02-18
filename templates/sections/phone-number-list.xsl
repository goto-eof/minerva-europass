<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="phone-number-list">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block-container text-align="left" font-size="10pt">
            <fo:block text-align="center">
                Phone numbers
            </fo:block>
            <fo:table>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-body>
                    <xsl:for-each select="$root/phoneNumberList/item">
                        <fo:table-row>
                            <fo:table-cell padding="2px">
                                <fo:block>
                                    <xsl:value-of select="name"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="2px">
                                <fo:block>
                                    <xsl:value-of select="value"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>
        </fo:block-container>
    </xsl:template>
</xsl:stylesheet>