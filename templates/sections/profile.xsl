<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:include href="generic-list.xsl"/>

    <xsl:template name="profile">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block text-align="left" font-size="8pt" margin="5px">
            <fo:table>
                <fo:table-column/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell font-size="14pt" font-weight="bold" padding-top="10px">
                            <fo:block text-align="center">
                                <xsl:value-of select="$root/firstName"/>
                                <xsl:value-of select="' '"/>
                                <xsl:value-of select="$root/lastName"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell padding-bottom="5px">
                            <fo:block text-align="center">
                                <xsl:value-of select="$root/city"/>
                                <xsl:value-of select="', '"/>
                                <xsl:value-of select="$root/county"/>
                                <xsl:value-of select="', '"/>
                                <xsl:value-of select="$root/country"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell padding-top="4px">
                            <fo:block text-align="left">
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="'Nationality: '"/>
                                </fo:inline>
                                <fo:inline>
                                    <xsl:value-of select="$root/citizenship"/>
                                </fo:inline>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell padding-top="4px">
                            <fo:block>
                                <fo:inline font-weight="bold">
                                    <xsl:value-of select="'Birth date: '"/>
                                </fo:inline>
                                <fo:inline>
                                    <xsl:value-of select="$root/birthDate"/>
                                </fo:inline>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding-top="10px">
                                <xsl:call-template name="generic-list">
                                    <xsl:with-param name="items" select="$root/phoneNumberList/item"/>
                                    <xsl:with-param name="title" select="'Phone numbers'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding-top="10px">
                                <xsl:call-template name="generic-list">
                                    <xsl:with-param name="items" select="$root/emailList/item"/>
                                    <xsl:with-param name="title" select="'E-Mails'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding-top="10px">
                                <xsl:call-template name="generic-list">
                                    <xsl:with-param name="items" select="$root/urlList/item"/>
                                    <xsl:with-param name="title" select="'URLs'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>