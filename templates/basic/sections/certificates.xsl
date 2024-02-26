<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="certificates">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block font-size="8pt">
            <fo:table>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell vertical-align="middle" number-columns-spanned="7">
                            <fo:block text-align="left">
                                <fo:block font-size="16pt" padding-bottom="10px" text-align="center">
                                    <xsl:value-of select="$root/certificatesTitle"/>
                                </fo:block>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <fo:table-cell number-columns-spanned="7">
                            <fo:block>
                                <fo:block font-size="10pt" text-align="justify">
                                    <xsl:value-of select="$root/certificatesDescription"/>
                                </fo:block>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block padding="3px"></fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <xsl:for-each select="$root/certificateList/item">
                        <fo:table-row>
                            <fo:table-cell>
                                <fo:block padding="3px"></fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding="3px" background-color="rgb(240,240,240)" number-columns-spanned="1">
                                <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                    <xsl:value-of select="'Data'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell background-color="rgb(240,240,240)" padding="3px" number-columns-spanned="6">
                                <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                    <xsl:value-of select="date"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding="3px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                                <fo:block margin-left="4px" wrap-option="wrap" text-align="justify"
                                          font-size="8pt">
                                    <xsl:value-of select="'Nome'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="3px" number-columns-spanned="6">
                                <fo:block margin-left="4px" wrap-option="wrap" text-align="justify"
                                          font-size="8pt">
                                    <xsl:value-of select="name"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding="3px" background-color="rgb(240,240,240)" number-columns-spanned="1">
                                <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                    <xsl:value-of select="'Descrizione'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="3px" number-columns-spanned="6">
                                <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                    <xsl:value-of select="description"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>

                        <xsl:if test="frontEndTechnologyList">
                            <fo:table-row>
                                <fo:table-cell padding="3px" background-color="rgb(240,240,240)"
                                               number-columns-spanned="1">
                                    <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                        <xsl:value-of select="'Tecnologie (front-end)'"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" number-columns-spanned="6">
                                    <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                        <xsl:value-of select="frontEndTechnologyList"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:if>
                        <xsl:if test="backEndTechnologyList">
                            <fo:table-row>
                                <fo:table-cell padding="3px" background-color="rgb(240,240,240)"
                                               number-columns-spanned="1">
                                    <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                        <xsl:value-of select="'Tecnologie (back-end)'"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding="3px" number-columns-spanned="6">
                                    <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                        <xsl:value-of select="backEndTechnologyList"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:if>

                        <fo:table-row>
                            <fo:table-cell padding="3px" background-color="rgb(240,240,240)" number-columns-spanned="1">
                                <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                    <xsl:value-of select="'Link'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding="3px" number-columns-spanned="6">
                                <fo:block margin-left="4px" wrap-option="wrap" text-align="justify" font-size="8pt">
                                    <xsl:value-of select="link"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>