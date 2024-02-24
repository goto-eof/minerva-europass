<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="other-skills">
        <xsl:param name="root" select="'default-value'"/>
        <fo:block font-size="10pt">
            <fo:table>
                <fo:table-column/>
                <fo:table-body>
                    <fo:table-row>
                        <fo:table-cell vertical-align="middle">
                            <fo:block text-align="left">
                                <fo:block font-size="16pt" padding-bottom="10px" text-align="center">
                                    <xsl:value-of select="$root/otherSkillsTitle"/>
                                </fo:block>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block>
                                <fo:block>
                                    <xsl:value-of select="$root/otherSkillsDescription"/>
                                </fo:block>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                    <fo:table-row>
                        <fo:table-cell>
                            <fo:block font-size="8pt" wrap-option="wrap">
                                <fo:table>
                                    <fo:table-column/>
                                    <fo:table-column/>
                                    <fo:table-column/>
                                    <fo:table-column/>
                                    <fo:table-column/>

                                    <fo:table-body>

                                        <fo:table-row>
                                            <fo:table-cell background-color="rgb(240,240,240)"
                                                           number-columns-spanned="1">
                                                <fo:block padding-top="4px"
                                                          padding-bottom="4px" margin-left="5px" margin-right="5px">
                                                    <xsl:value-of select="'Capacità e competenze sociali'"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell number-columns-spanned="4">
                                                <fo:block margin-left="10px" padding-top="4px" padding-bottom="4px">
                                                    <xsl:value-of select="$root/otherSkillSocialList"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>


                                        <fo:table-row>
                                            <fo:table-cell background-color="rgb(240,240,240)"
                                                           number-columns-spanned="1">
                                                <fo:block padding-top="4px"
                                                          padding-bottom="4px" margin-left="5px" margin-right="5px">
                                                    <xsl:value-of select="'Capacità e competenze organizzative'"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell number-columns-spanned="4">
                                                <fo:block margin-left="10px">
                                                    <xsl:value-of select="$root/otherSkillOrganizationalList"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>

                                        <fo:table-row>
                                            <fo:table-cell background-color="rgb(240,240,240)"
                                                           number-columns-spanned="1">
                                                <fo:block padding-top="4px"
                                                          padding-bottom="4px" margin-left="5px" margin-right="5px">
                                                    <xsl:value-of select="'Altre capacità e competenze'"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell number-columns-spanned="4">
                                                <fo:block margin-left="10px">
                                                    <xsl:value-of select="$root/otherSkillOtherList"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>


                                    </fo:table-body>
                                </fo:table>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>

                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>
</xsl:stylesheet>