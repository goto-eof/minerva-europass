<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="experience-item">
        <xsl:param name="item" select="'default-value'"/>
        <xsl:param name="title" select="'default-value'"/>
        <fo:block text-align="left" font-size="10pt">

            <fo:table font-size="8pt">
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-body>

                    <fo:table-row>
                        <fo:table-cell number-columns-spanned="5">
                            <fo:block text-align="left" padding="10px" font-weight="bold">
                                <xsl:value-of select="$title"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row background-color="rgb(240,240,240)">
                        <fo:table-cell number-columns-spanned="1">
                            <fo:block padding-top="4px" padding-bottom="4px" margin-left="5px">
                                <xsl:value-of select="'Date'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/dateFrom"/>
                                <xsl:value-of select="' - '"/>
                                <xsl:value-of select="$item/dateTo"/>
                                <xsl:if test="$item/jobDuration">
                                    <xsl:value-of select="' • '"/>
                                    <xsl:value-of select="$item/jobDuration"/>
                                </xsl:if>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <xsl:if test="$item/jobTitle">
                        <fo:table-row>
                            <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                           background-color="rgb(240,240,240)">
                                <fo:block padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="'Posizioni ricoperte'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell number-columns-spanned="4">
                                <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="$item/jobTitle"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>

                    <xsl:if test="$item/mainActivities">
                        <fo:table-row>
                            <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                           background-color="rgb(240,240,240)">
                                <fo:block padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="'Principali attività'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell number-columns-spanned="4">
                                <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="$item/mainActivities"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>


                    <xsl:if test="$item/name">
                        <fo:table-row>
                            <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                           background-color="rgb(240,240,240)">
                                <fo:block padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="'Nome'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell number-columns-spanned="4">
                                <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="$item/name"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>

                    <fo:table-row>
                        <fo:table-cell margin-left="5px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                            <fo:block padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="'Descrizione'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/description"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <xsl:if test="$item/frontEndTechnologyList">
                        <fo:table-row>
                            <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                           background-color="rgb(240,240,240)">
                                <fo:block padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="'Tecnologie (front-end)'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell number-columns-spanned="4">
                                <xsl:if test="$item/isWorkedAsFrontEndDeveloper = 'true' ">
                                    <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                        <xsl:value-of select="$item/frontEndTechnologyList"/>
                                    </fo:block>
                                </xsl:if>
                                <xsl:if test="$item/isWorkedAsFrontEndDeveloper = 'false' ">
                                    <fo:block margin-left="5px" padding-top="4px" color="gray" padding-bottom="4px">
                                        <xsl:value-of select="$item/frontEndTechnologyList"/>
                                    </fo:block>
                                </xsl:if>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>

                    <xsl:if test="$item/backEndTechnologyList">
                        <fo:table-row>
                            <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                           background-color="rgb(240,240,240)">
                                <fo:block padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="'Tecnologie (back-end)'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell number-columns-spanned="4">
                                <xsl:if test="$item/isWorkedAsBackEndDeveloper = 'true' ">
                                    <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                        <xsl:value-of select="$item/backEndTechnologyList"/>
                                    </fo:block>
                                </xsl:if>
                                <xsl:if test="$item/isWorkedAsBackEndDeveloper = 'false'">
                                    <fo:block margin-left="5px" padding-top="4px" color="gray" padding-bottom="4px">
                                        <xsl:value-of select="$item/backEndTechnologyList"/>
                                    </fo:block>
                                </xsl:if>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>

                    <xsl:if test="$item/toolList">
                        <fo:table-row>
                            <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                           background-color="rgb(240,240,240)">
                                <fo:block padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="'Strumenti'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell number-columns-spanned="4">
                                <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="$item/toolList"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>


                    <xsl:if test="$item/company">
                        <fo:table-row>
                            <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                           background-color="rgb(240,240,240)">
                                <fo:block padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="'Azienda'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell number-columns-spanned="4">
                                <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="$item/company"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>

                    <xsl:if test="$item/sector">
                        <fo:table-row>
                            <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                           background-color="rgb(240,240,240)">
                                <fo:block padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="'Settore'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell number-columns-spanned="4">
                                <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="$item/sector"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>

                    <xsl:if test="$item/url">
                        <fo:table-row>
                            <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                           background-color="rgb(240,240,240)">
                                <fo:block padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="'URL'"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell number-columns-spanned="4">
                                <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                    <xsl:value-of select="$item/url"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:if>

                    <xsl:if test="$item/urlList">
                        <xsl:for-each select="$item/urlList/item">
                            <fo:table-row>
                                <fo:table-cell margin-left="5px" number-columns-spanned="1"
                                               background-color="rgb(240,240,240)">
                                    <fo:block padding-top="4px" padding-bottom="4px">
                                        <xsl:value-of select="description"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell number-columns-spanned="4">
                                    <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                        <xsl:value-of select="url"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:for-each>
                    </xsl:if>

                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>