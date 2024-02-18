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
                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="'Firstname and Lastname:'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="$root/firstname"/>
                                <xsl:value-of select="' '"/>
                                <xsl:value-of select="$root/lastname"/>
                            </fo:block>
                        </fo:table-cell>

                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="'Citizenship:'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="$root/citizenship"/>
                            </fo:block>
                        </fo:table-cell>

                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="'Birth date:'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="$root/birthDate"/>
                            </fo:block>
                        </fo:table-cell>

                    </fo:table-row>


                    <fo:table-row>
                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="'City:'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="2px">
                            <fo:block>
                                <xsl:value-of select="$root/city"/>
                                <xsl:value-of select="', '"/>
                                <xsl:value-of select="$root/county"/>
                                <xsl:value-of select="', '"/>
                                <xsl:value-of select="$root/country"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <xsl:for-each select="$root/emailList/item">
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

                    <xsl:for-each select="$root/urlList/item">
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
        </fo:block>
    </xsl:template>
</xsl:stylesheet>