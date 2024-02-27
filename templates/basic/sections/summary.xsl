<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">

    <xsl:template name="summary">
        <xsl:param name="root" select="'default-value'"/>
        <xsl:param name="title" select="'default-value'"/>
        <xsl:param name="topX" select="'default-value'"/>
        <xsl:param name="topRoles" select="'default-value'"/>
        <xsl:param name="yearsOfExperiencePerTechBackEnd" select="'default-value'"/>
        <xsl:param name="yearsOfExperiencePerTechFrontEnd" select="'default-value'"/>
        <xsl:param name="yearsExperienceByField" select="'default-value'"/>

        <fo:table margin-top="10px" border="1px solid gray" font-size="7pt" space-before="0.5em" space-after="0.5em">

            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>
            <fo:table-column/>

            <fo:table-body>
                <fo:table-row>
                    <fo:table-cell background-color="rgb(240,240,240)" number-columns-spanned="7">
                        <fo:block text-align="center" padding="4px">
                            <xsl:value-of select="$title"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>

                <xsl:for-each select="$topX/item">
                    <fo:table-row>
                        <fo:table-cell background-color="rgb(240,240,240)" number-columns-spanned="1">
                            <fo:block padding="4px" margin-left="2px">
                                <xsl:value-of select="key"/>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell padding="4px" number-columns-spanned="6">
                            <fo:block margin-left="10px" text-align="left">
                                <xsl:value-of select="value"/>
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>

                <fo:table-row>
                    <fo:table-cell background-color="rgb(240,240,240)" number-columns-spanned="1">
                        <fo:block padding="4px" margin-left="2px">
                            <xsl:value-of select="'Anni di esperienza relativi alla singola tecnologia del back-end'"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="4px" number-columns-spanned="6">
                        <fo:block margin-left="10px" text-align="left">
                            <xsl:value-of select="$yearsOfExperiencePerTechBackEnd"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>

                <fo:table-row>
                    <fo:table-cell background-color="rgb(240,240,240)" number-columns-spanned="1">
                        <fo:block padding="4px" margin-left="2px">
                            <xsl:value-of select="'Anni de esperienza relativi alla singola tecnologia del front-end'"/>
                        </fo:block>
                    </fo:table-cell>
                    <fo:table-cell padding="4px" number-columns-spanned="6">
                        <fo:block margin-left="10px" text-align="left">
                            <xsl:value-of select="$yearsOfExperiencePerTechFrontEnd"/>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>


                <fo:table-row>
                    <fo:table-cell number-columns-spanned="7" border-top="1px solid gray"
                                   border-bottom="1px solid gray">
                        <fo:block>
                            <fo:table>
                                <fo:table-column/>
                                <fo:table-column/>
                                <fo:table-column/>
                                <fo:table-column/>
                                <fo:table-body>
                                    <fo:table-row>
                                        <xsl:for-each select="$topRoles/item">
                                            <fo:table-cell background-color="rgb(240,240,240)"
                                                           number-columns-spanned="1">
                                                <fo:block padding="4px" margin-left="2px">
                                                    <xsl:value-of select="name"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="4px" number-columns-spanned="1">
                                                <fo:block margin-left="10px" text-align="left">
                                                    <xsl:value-of select="value"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>
                                    </fo:table-row>
                                    <fo:table-row>
                                        <xsl:for-each select="$yearsExperienceByField/item">
                                            <fo:table-cell padding="4px" background-color="rgb(240,240,240)"
                                                           number-columns-spanned="1">
                                                <fo:block margin-left="2px">
                                                    <xsl:value-of select="name"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell padding="4px" number-columns-spanned="1">
                                                <fo:block margin-left="10px" text-align="left">
                                                    <xsl:value-of select="value"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </xsl:for-each>
                                    </fo:table-row>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>
                <fo:table-row>
                    <fo:table-cell number-columns-spanned="7">
                        <fo:block color="gray" margin-left="4px" padding="4px" font-size="7pt">* questo riepilogo
                            potrebbe tenere conto della data di generazione del
                            documento in
                            questione, la quale è<xsl:value-of select="$root/generatedOn"/>.
                        </fo:block>
                    </fo:table-cell>
                </fo:table-row>

            </fo:table-body>
        </fo:table>
    </xsl:template>
</xsl:stylesheet>