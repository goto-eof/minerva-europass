<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="header">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block text-align="right" font-size="10pt">
            <fo:table>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell vertical-align="middle">
                            <fo:block text-align="left">

                                <fo:block margin-left="0px">
                                    <fo:external-graphic src="classpath:static/icon.png"
                                                         content-height="32px"
                                                         content-width="32px"/>
                                </fo:block>
                                <fo:block font-size="7pt">IT Europass</fo:block>
                            </fo:block>
                        </fo:table-cell>

                        <fo:table-cell>
                            <fo:block>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block wrap-option="wrap">
                                <xsl:value-of select="$root/companyDescription"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>