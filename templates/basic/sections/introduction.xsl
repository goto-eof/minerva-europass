<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="introduction">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block font-size="10pt">
            <fo:table>
                <fo:table-column/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell vertical-align="middle">
                            <fo:block text-align="left">
                                <fo:block font-size="16pt" padding-bottom="10px" text-align="center">
                                    <xsl:value-of select="$root/introductionTitle"/>
                                </fo:block>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <fo:table-cell min-height="21cm">
                            <fo:block font-size="8pt" linefeed-treatment="preserve" wrap-option="wrap"
                                      text-align="justify">
                                <xsl:value-of select="$root/introductionContent"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <fo:table-cell min-height="2cm">
                            <fo:block font-size="6pt" color="gray" linefeed-treatment="preserve" wrap-option="wrap">
                                <xsl:value-of select="$root/introductionFooter"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>