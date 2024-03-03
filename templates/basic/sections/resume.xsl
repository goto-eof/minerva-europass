<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:include href="profile.xsl"/>
    <xsl:include href="introduction.xsl"/>
    <xsl:template name="resume">
        <xsl:param name="root" select="nodeset"/>
        <fo:block page-break-after="always">
            <fo:table table-layout="fixed" width="100%" font-size="7pt" space-before="0.5em" space-after="0.5em">

                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>
                <fo:table-column/>

                <fo:table-body height="100pvh">
                    <fo:table-row>
                        <fo:table-cell number-columns-spanned="6">
                            <fo:block font-size="18pt" padding-bottom="10px" text-align="center">
                                Curriculum Vitae
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <xsl:if test="$root/enableProfile = 'true'">
                            <fo:table-cell background-color="rgb(240,240,240)" number-columns-spanned="2">
                                <fo:block-container>
                                    <xsl:call-template name="profile">
                                        <xsl:with-param name="root" select="$root"/>
                                    </xsl:call-template>
                                </fo:block-container>
                            </fo:table-cell>
                        </xsl:if>
                        <xsl:if test="$root/enableProfile != 'true'">
                            <fo:table-cell>
                                <fo:block></fo:block>
                            </fo:table-cell>
                        </xsl:if>
                        <xsl:if test="$root/enableIntroduction = 'true'">
                            <fo:table-cell number-columns-spanned="4">
                                <fo:block-container min-height="25cm" margin-left="3px" margin-right="3px">
                                    <xsl:call-template name="introduction">
                                        <xsl:with-param name="root" select="$root"/>
                                    </xsl:call-template>
                                </fo:block-container>
                            </fo:table-cell>
                        </xsl:if>
                        <xsl:if test="$root/enableIntroduction != 'true'">
                            <fo:table-cell>
                                <fo:block></fo:block>
                            </fo:table-cell>
                        </xsl:if>
                    </fo:table-row>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>