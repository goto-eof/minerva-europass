<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:include href="sections/header.xsl"/>
    <xsl:include href="sections/footer.xsl"/>
    <xsl:include href="sections/resume.xsl"/>

    <xsl:output method="xml" version="1.0" omit-xml-declaration="no" indent="yes"/>
    <xsl:param name="version" select="'1.0'"/>
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="simpleA4"
                                       page-height="29.7cm" page-width="21cm" margin-top="1cm" margin-bottom="1cm"
                                       margin-left="1cm" margin-right="1cm">
                    <fo:region-body margin-top="0.2cm"/>
                    <fo:region-before region-name="header-region"/>
                    <fo:region-after region-name="footer-region"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="simpleA4" initial-page-number="1" id="end">
                <fo:static-content flow-name="header-region" font-size="10pt">
                    <xsl:call-template name="header">
                        <xsl:with-param name="root" select="root"/>
                    </xsl:call-template>
                </fo:static-content>

                <fo:static-content flow-name="footer-region" font-size="10pt">
                    <xsl:call-template name="footer">
                        <xsl:with-param name="root" select="root"/>
                    </xsl:call-template>
                </fo:static-content>

                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="20pt" text-align="center" padding="10px">
                        <xsl:value-of select="root/title"/>
                    </fo:block>
                    <fo:block-container height="26cm" padding-top="0px">
                        <xsl:call-template name="resume">
                            <xsl:with-param name="root" select="root"/>
                        </xsl:call-template>
                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>