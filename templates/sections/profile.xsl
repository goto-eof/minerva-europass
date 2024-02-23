<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:include href="generic-map.xsl"/>
    <xsl:include href="generic-list.xsl"/>
    <xsl:include href="generic-item.xsl"/>
    <xsl:include href="generic-item-same-row.xsl"/>

    <xsl:template name="profile">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block text-align="left" font-size="8pt">
            <fo:table>
                <fo:table-column/>
                <fo:table-body margin-left="5px" margin-right="5px">
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block font-size="15pt" padding-top="10pt" padding-bottom="10pt" text-align="center">
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block text-align="center">
                                <fo:external-graphic content-width="128px" content-height="128px"
                                                     src="classpath:static/user.png"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell font-size="14pt" font-weight="bold" padding-top="10px">
                            <fo:block text-align="center" margin-bottom="5px">

                                <fo:table border-collapse="separate" border-spacing="1pt 0pt">
                                    <fo:table-column/>
                                    <fo:table-column/>
                                    <fo:table-body>
                                        <fo:table-row>
                                            <fo:table-cell>
                                                <fo:block text-align="right">
                                                    <xsl:value-of select="$root/firstName"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block text-align="left">
                                                    <xsl:value-of select="$root/lastName"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                        <fo:table-row>
                                            <fo:table-cell>
                                                <fo:block font-weight="normal" font-size="5pt" text-align="right">
                                                    <xsl:value-of select="'(first name)'"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block font-weight="normal" font-size="5pt" text-align="left">
                                                    <xsl:value-of select="'(last name)'"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </fo:table-body>
                                </fo:table>


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
                            <fo:block>
                                <xsl:call-template name="generic-item-same-row">
                                    <xsl:with-param name="value" select="$root/citizenship"/>
                                    <xsl:with-param name="title" select="'Nationality'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell padding-top="4px">
                            <fo:block>
                                <xsl:call-template name="generic-item-same-row">
                                    <xsl:with-param name="value" select="$root/birthDate"/>
                                    <xsl:with-param name="title" select="'Birth date'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding-top="10px">
                                <xsl:call-template name="generic-map">
                                    <xsl:with-param name="items" select="$root/phoneNumberList/item"/>
                                    <xsl:with-param name="title" select="'Phone numbers'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding-top="10px">
                                <xsl:call-template name="generic-map">
                                    <xsl:with-param name="items" select="$root/emailList/item"/>
                                    <xsl:with-param name="title" select="'E-Mails'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding-top="10px">
                                <xsl:call-template name="generic-map">
                                    <xsl:with-param name="items" select="$root/urlList/item"/>
                                    <xsl:with-param name="title" select="'URLs'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding-top="10px">
                                <xsl:call-template name="generic-item">
                                    <xsl:with-param name="value" select="$root/mainSkills"/>
                                    <xsl:with-param name="title" select="'Main skills'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding-top="10px">
                                <xsl:call-template name="generic-item">
                                    <xsl:with-param name="value" select="$root/languages"/>
                                    <xsl:with-param name="title" select="'Languages'"/>
                                </xsl:call-template>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>