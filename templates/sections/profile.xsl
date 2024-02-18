<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="profile">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block text-align="left" font-size="10pt">
            <fo:table>
                <fo:table-column/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="$root/firstname"/>
                                <xsl:value-of select="' '"/>
                                <xsl:value-of select="$root/lastname"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="'Nationality:'"/>
                                <xsl:value-of select="$root/citizenship"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="'Birth date:'"/>
                                <xsl:value-of select="$root/birthDate"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>