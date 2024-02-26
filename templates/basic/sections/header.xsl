<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="header">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block text-align="right" font-size="10pt" color="gray">
            <fo:table>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell vertical-align="middle">
                            <fo:block text-align="left">
                                <fo:block font-size="7pt">
                                    <xsl:value-of select="$root/applicationName"/> |
                                    <xsl:value-of select="'generato il '"/>
                                    <xsl:value-of select="$root/generatedOn"/>
                                </fo:block>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block text-align="center">
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block font-size="7pt" wrap-option="wrap">
                                Curriculum Vitae |
                                <xsl:value-of select="' '"/>
                                <xsl:value-of select="$root/firstName"/>
                                <xsl:value-of select="' '"/>
                                <xsl:value-of select="$root/lastName"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>