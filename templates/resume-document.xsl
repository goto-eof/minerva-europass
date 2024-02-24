<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:include href="sections/header.xsl"/>
    <xsl:include href="sections/footer.xsl"/>
    <xsl:include href="sections/resume.xsl"/>
    <xsl:include href="sections/experience.xsl"/>
    <xsl:include href="sections/personal-projects.xsl"/>

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

            <fo:declarations>
                <x:xmpmeta xmlns:x="adobe:ns:meta/">
                    <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
                        <rdf:Description rdf:about=""
                                         xmlns:dc="http://purl.org/dc/elements/1.1/">
                            <!-- Dublin Core properties go here -->
                            <dc:title>
                                <xsl:value-of select="root/applicationName"/>
                                <xsl:value-of select="' | '"/>
                                <xsl:value-of select="root/firstName"/>
                                <xsl:value-of select="' '"/>
                                <xsl:value-of select="root/lastName"/>
                            </dc:title>
                            <dc:creator>
                                <xsl:value-of select="root/applicationName"/>
                            </dc:creator>

                            <dc:description>
                                <xsl:value-of select="root/applicationName"/>
                            </dc:description>
                        </rdf:Description>
                        <rdf:Description rdf:about=""
                                         xmlns:xmp="http://ns.adobe.com/xap/1.0/">
                            <!-- XMP properties go here -->
                            <xmp:CreatorTool>
                                <fo:block>
                                    <xsl:value-of select="root/applicationName"/>
                                </fo:block>
                            </xmp:CreatorTool>
                        </rdf:Description>
                    </rdf:RDF>
                </x:xmpmeta>
            </fo:declarations>

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
                    <fo:block-container min-height="26cm" padding-top="0px">
                        <xsl:call-template name="resume">
                            <xsl:with-param name="root" select="root"/>
                        </xsl:call-template>
                    </fo:block-container>
                    <fo:block-container min-height="26cm" padding-top="0px">
                        <xsl:call-template name="experience">
                            <xsl:with-param name="root" select="root"/>
                        </xsl:call-template>
                    </fo:block-container>
                    <fo:block-container min-height="26cm" padding-top="0px">
                        <xsl:call-template name="personal-projects">
                            <xsl:with-param name="root" select="root"/>
                        </xsl:call-template>
                    </fo:block-container>
                </fo:flow>
            </fo:page-sequence>

        </fo:root>
    </xsl:template>

</xsl:stylesheet>