<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="job-experience">
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
                                <xsl:value-of select="'Dates'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/dateFrom"/>
                                <xsl:value-of select="' - '"/>
                                <xsl:value-of select="$item/dateTo"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell margin-left="5px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                            <fo:block padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="'Job title'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/jobTitle"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>


                    <fo:table-row>
                        <fo:table-cell margin-left="5px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                            <fo:block padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="'Description'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/description"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>


                    <fo:table-row>
                        <fo:table-cell margin-left="5px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                            <fo:block padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="'Front-end technologies'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/frontEndTechnologyList"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell margin-left="5px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                            <fo:block padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="'Back-end technologies'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/backEndTechnologyList"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell margin-left="5px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                            <fo:block padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="'Tools'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/toolList"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>


                    <fo:table-row>
                        <fo:table-cell margin-left="5px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                            <fo:block padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="'Main activities'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/mainActivities"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>


                    <fo:table-row>
                        <fo:table-cell margin-left="5px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                            <fo:block padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="'Customer'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/customer"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell margin-left="5px" number-columns-spanned="1" background-color="rgb(240,240,240)">
                            <fo:block padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="'Sector'"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell number-columns-spanned="4">
                            <fo:block margin-left="5px" padding-top="4px" padding-bottom="4px">
                                <xsl:value-of select="$item/sector"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>